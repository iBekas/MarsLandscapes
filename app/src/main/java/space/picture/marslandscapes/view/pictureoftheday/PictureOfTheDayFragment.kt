package space.picture.marslandscapes.view.pictureoftheday

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.*
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import space.picture.marslandscapes.BuildConfig
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.PictureOfTheDayStartFragmentBinding
import space.picture.marslandscapes.model.WIKI_URL
import space.picture.marslandscapes.util.getDaysAgo
import space.picture.marslandscapes.util.toast
import space.picture.marslandscapes.view.MainActivity
import space.picture.marslandscapes.view.roverphoto.MarsRoverPhotoActivity
import space.picture.marslandscapes.view.settings.SettingsFragment
import space.picture.marslandscapes.viewmodel.AppState
import space.picture.marslandscapes.viewmodel.PictureOfTheDayViewModel
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var isExpanded = false

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    private var _binding: PictureOfTheDayStartFragmentBinding? = null
    private val binding: PictureOfTheDayStartFragmentBinding
        get(): PictureOfTheDayStartFragmentBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PictureOfTheDayStartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchWikipedia()
        setMenuOnBottomBar()
        setDescription()
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getPictureTodayFromRemoteSource(BuildConfig.NASA_API_KEY)
        onClickPictureChips()
    }

    private fun setMenuOnBottomBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_settings -> activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_fragment,
                        R.anim.exit_fragment,
                        R.anim.enter_fragment_in,
                        R.anim.exit_fragment_out
                    )
                    .add(
                    R.id.container,
                    SettingsFragment.newInstance()
                ).addToBackStack(null).commit()
            }
            R.id.app_bar_rover -> startActivity(Intent(context, MarsRoverPhotoActivity::class.java))
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }


        return super.onOptionsItemSelected(item)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> toast("Ошибка загрузки")
            is AppState.Success -> {
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
                    binding.bottomSheetDescriptionHeader.text =
                        appState.dataNasa.title
                    binding.bottomSheetDescription.text =
                        appState.dataNasa.explanation
                }
            }
            is AppState.Loading -> binding.pictureLoading.visibility = View.VISIBLE
        }
    }


    private fun searchWikipedia() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$WIKI_URL${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun onClickPictureChips() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            with(binding) {
                when (checkedId) {
                    R.id.chipToday -> {
                        viewModel.getPictureTodayFromRemoteSource(
                            BuildConfig.NASA_API_KEY
                        )
                    }
                    R.id.chipYesterday -> {
                        chipToday.isChecked = false
                        viewModel.getPictureYesterdayFromRemoteSource(
                            getDaysAgo(1),
                            BuildConfig.NASA_API_KEY
                        )
                    }
                    R.id.chipBeforeYesterday -> {
                        chipToday.isChecked = false
                        viewModel.getPictureYesterdayFromRemoteSource(
                            getDaysAgo(2),
                            BuildConfig.NASA_API_KEY
                        )
                    }
                }
            }
         }
    }

    private fun setDescription() {
        binding.showDetails.setOnClickListener {
            if (!isExpanded) expandDescription()
        }
        binding.bottomSheetDescription.setOnClickListener {
            if (isExpanded) collapseDescription()
        }
        binding.bottomSheetDescriptionHeader.setOnClickListener {
            if (isExpanded) collapseDescription()
        }
        binding.bottomSheetLine.setOnClickListener {
            if (isExpanded) collapseDescription()
        }
    }

    private fun expandDescription() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.descriptionContainer, "translationY", -50f).start()
        binding.descriptionContainer.animate().duration = 300
    }

    private fun collapseDescription() {
        isExpanded = false
        ObjectAnimator.ofFloat(binding.descriptionContainer, "translationY", 500f).start()
        binding.descriptionContainer.animate().duration = 300
    }
}



