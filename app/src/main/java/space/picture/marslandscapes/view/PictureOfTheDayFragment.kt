package space.picture.marslandscapes.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import space.picture.marslandscapes.BuildConfig
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.PictureOfTheDayFragmentBinding
import space.picture.marslandscapes.viewmodel.AppState
import space.picture.marslandscapes.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    private var _binding: PictureOfTheDayFragmentBinding? = null
    private val binding: PictureOfTheDayFragmentBinding
        get(): PictureOfTheDayFragmentBinding {
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
        _binding = PictureOfTheDayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        searchWikipedia()
        viewModel.getLiveData().observe(viewLifecycleOwner,
            Observer<AppState> { renderData(it) })
        viewModel.getPictureTodayFromRemoteSource(BuildConfig.NASA_API_KEY)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> toast("Ошибка загрузки")
            is AppState.SuccessToday -> {
                binding.pictureLoading.visibility = View.GONE
                val serverResponseData = appState.dataNasa
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                    view?.findViewById<TextView>(R.id.bottom_sheet_description_header)?.text = appState.dataNasa.title
                    view?.findViewById<TextView>(R.id.bottom_sheet_description)?.text = appState.dataNasa.explanation
                }
            }
            is AppState.SuccessYesterday -> binding.pictureLoading.visibility = View.GONE
            is AppState.Loading -> binding.pictureLoading.visibility = View.VISIBLE
        }
    }


    private fun searchWikipedia() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (i == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            override fun onSlide(view: View, v: Float) {
                // do something when slide happens
            }
        })
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

}