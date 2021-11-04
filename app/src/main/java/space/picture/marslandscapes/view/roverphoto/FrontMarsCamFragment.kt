package space.picture.marslandscapes.view.roverphoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.api.load
import space.picture.marslandscapes.BuildConfig
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.FrontMarsCamFragmentBinding
import space.picture.marslandscapes.model.FRONT_CAM
import space.picture.marslandscapes.model.SOL_FOR_ROVER_CAM
import space.picture.marslandscapes.util.toast
import space.picture.marslandscapes.viewmodel.AppState
import space.picture.marslandscapes.viewmodel.MarsRoverPhotoViewModel

class FrontMarsCamFragment : Fragment() {

    private var isExpanded = false

    private val viewModel: MarsRoverPhotoViewModel by lazy {
        ViewModelProvider(this).get(MarsRoverPhotoViewModel::class.java)
    }

    private var _binding: FrontMarsCamFragmentBinding? = null
    private val binding: FrontMarsCamFragmentBinding
        get(): FrontMarsCamFragmentBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FrontMarsCamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animImage()
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getPictureOfMarsRover(SOL_FOR_ROVER_CAM, FRONT_CAM, BuildConfig.NASA_API_KEY)
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Error -> toast("Ошибка загрузки")
            is AppState.SuccessRoverPhoto -> {
                binding.pictureLoading.visibility = View.GONE
                val serverResponseData = appState.dataNasa[0]
                val url = serverResponseData.img_src
                Log.d("myLog", url + "")
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    binding.frontRoverCam.load(url) {
                        lifecycle(this@FrontMarsCamFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.bg_system)
                    }
                }
            }
            is AppState.Loading -> binding.pictureLoading.visibility = View.VISIBLE
            else -> binding.pictureLoading.visibility = View.VISIBLE
        }
    }

    private fun animImage() {
        binding.frontRoverCam.setOnClickListener {
            isExpanded= !isExpanded

            val set = TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())

            TransitionManager.beginDelayedTransition(binding.frontContainer,set)
            binding.frontRoverCam.scaleType = if(isExpanded){
                ImageView.ScaleType.CENTER_CROP
            }else{
                ImageView.ScaleType.FIT_CENTER
            }
        }
    }
}
