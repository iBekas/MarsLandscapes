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

    private var isExpanded = false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFabs()
    }

    companion object {
        fun newInstance() = NotesFragment()
    }

    private fun showFabs() {
        binding.recyclerNotesFAB.setOnClickListener {
            if(isExpanded){
                isExpanded = false
                binding.plusNote.visibility = View.GONE
                binding.plusCake.visibility = View.GONE
                binding.plusNote.isClickable = false
                binding.plusCake.isClickable = false
                binding.recyclerNotesFAB.alpha = 1f
            } else {
                isExpanded = true
                binding.plusNote.visibility = View.VISIBLE
                binding.plusCake.visibility = View.VISIBLE
                binding.plusNote.isClickable = true
                binding.plusCake.isClickable = true
                binding.recyclerNotesFAB.alpha = 0.5f
            }
        }
    }
}
