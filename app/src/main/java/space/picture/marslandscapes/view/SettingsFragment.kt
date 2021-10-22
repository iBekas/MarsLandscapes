package space.picture.marslandscapes.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.SettingsFragmentBinding
import space.picture.marslandscapes.util.App

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding: SettingsFragmentBinding
        get(): SettingsFragmentBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        setChipChecked(binding.chipStandardTheme.isChecked, binding.chipBlueberryTheme.isChecked)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        binding.chipStandardTheme.isChecked = getChipCheckedStandard()
        binding.chipBlueberryTheme.isChecked = getChipCheckedBlueberry()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickThemeChips()
    }

    private fun onClickThemeChips() {
        App.fragmentId = 1
        with(binding) {
            chipStandardTheme.setOnClickListener {
                chipStandardTheme.isChecked = false
                setAppTheme(R.style.AppTheme)
                activity?.recreate()
                chipStandardTheme.isChecked = true
            }

            chipBlueberryTheme.setOnClickListener {
                chipStandardTheme.isChecked = false
                setAppTheme(R.style.Blueberry)
                activity?.recreate()
            }
        }
    }

    private fun setAppTheme(codeStyle: Int) {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            THEME_SHARED_PREFERENCE,
            Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putInt(THEME, codeStyle)
        editor.apply()
    }

    private fun setChipChecked(standard: Boolean, blueberry: Boolean) {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            CHECK_CHIPS_STATE,
            Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putBoolean(STANDARD, standard)
        editor.putBoolean(BLUEBERRY, blueberry)
        editor.apply()
    }

    private fun getChipCheckedStandard(): Boolean {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            CHECK_CHIPS_STATE,
            Context.MODE_PRIVATE
        )
        return sharedPref.getBoolean(STANDARD, true)
    }

    private fun getChipCheckedBlueberry(): Boolean {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            CHECK_CHIPS_STATE,
            Context.MODE_PRIVATE
        )
        return sharedPref.getBoolean(BLUEBERRY, false)
    }

    companion object {
        fun newInstance() = SettingsFragment()
        const val THEME_SHARED_PREFERENCE = "KEY"
        const val THEME = "THEME"
        const val CHECK_CHIPS_STATE = "CHECKED"
        const val BLUEBERRY = "BLUEBERRY"
        const val STANDARD = "STANDARD"
    }
}
