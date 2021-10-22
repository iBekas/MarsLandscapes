package space.picture.marslandscapes.view

import android.os.Bundle
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
                (requireActivity() as? MainActivity)?.changeTheme(R.style.AppTheme)
                chipStandardTheme.isChecked = true
            }

            chipBlueberryTheme.setOnClickListener {
                chipStandardTheme.isChecked = false
                (requireActivity() as? MainActivity)?.changeTheme(R.style.Blueberry)
            }
        }

    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
