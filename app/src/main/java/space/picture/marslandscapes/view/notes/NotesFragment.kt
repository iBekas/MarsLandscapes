package space.picture.marslandscapes.view.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.FragmentNotesBinding
import space.picture.marslandscapes.databinding.PictureOfTheDayStartFragmentBinding
import space.picture.marslandscapes.databinding.SettingsFragmentBinding

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding: FragmentNotesBinding
        get(): FragmentNotesBinding {
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
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = NotesFragment()

    }
}
