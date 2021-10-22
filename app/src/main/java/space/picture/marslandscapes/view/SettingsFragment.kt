package space.picture.marslandscapes.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.SettingsFragmentBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickThemeChips()
    }

    private fun onClickThemeChips() {
        with(binding){
            chipStandardTheme.setOnClickListener {
                chipStandardTheme.isChecked = false
                setAppTheme(R.style.AppTheme)
//                (activity as? MainActivity)?.changeTheme(R.style.AppTheme)
                activity?.recreate()
                chipStandardTheme.isChecked = true
            }

            chipBlueberryTheme.setOnClickListener {
                chipStandardTheme.isChecked = false
                setAppTheme(R.style.Blueberry)
                activity?.recreate()
//                (activity as? MainActivity)?.changeTheme(R.style.Blueberry)
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

    fun getAppTheme(): Int {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            THEME_SHARED_PREFERENCE,
            Context.MODE_PRIVATE
        )
        return sharedPref.getInt(THEME, R.style.AppTheme)
    }


    companion object {
        fun newInstance() = SettingsFragment()
        const val THEME_SHARED_PREFERENCE = "KEY"
        const val THEME = "THEME"

    }
}
