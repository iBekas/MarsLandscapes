package space.picture.marslandscapes.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import space.picture.marslandscapes.BuildConfig
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.PictureOfTheDayFragmentBinding
import space.picture.marslandscapes.model.WIKI_URL
import space.picture.marslandscapes.util.getDaysAgo
import space.picture.marslandscapes.util.toast
import space.picture.marslandscapes.view.marsweather.MarsWeatherActivity
import space.picture.marslandscapes.viewmodel.AppState
import space.picture.marslandscapes.viewmodel.PictureOfTheDayViewModel
import java.text.SimpleDateFormat
import java.util.*

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
    ): View {
        _binding = PictureOfTheDayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior()
        searchWikipedia()
        setMenuOnBottomBar()
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
                beginTransaction().replace(
                    R.id.container,
                    SettingsFragment.newInstance()
                ).addToBackStack(null).commit()
            }
            R.id.app_bar_rover -> startActivity(Intent(context,MarsWeatherActivity::class.java))
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
                    binding.includeLayout.bottomSheetDescriptionHeader.text =
                        appState.dataNasa.title
                    binding.includeLayout.bottomSheetDescription.text =
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

    private fun setBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun onClickPictureChips() {
        binding.chipToday.setOnClickListener {
            binding.chipToday.isChecked = false
            viewModel.getPictureTodayFromRemoteSource(
                BuildConfig.NASA_API_KEY
            )
            binding.chipToday.isChecked = true
        }
        binding.chipYesterday.setOnClickListener {
            binding.chipToday.isChecked = false
            viewModel.getPictureYesterdayFromRemoteSource(
                getDaysAgo(1),
                BuildConfig.NASA_API_KEY
            )
        }
        binding.chipBeforeYesterday.setOnClickListener {
            binding.chipToday.isChecked = false
            viewModel.getPictureYesterdayFromRemoteSource(
                getDaysAgo(2),
                BuildConfig.NASA_API_KEY
            )
        }
    }

}