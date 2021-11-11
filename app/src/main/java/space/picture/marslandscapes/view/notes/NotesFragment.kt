package space.picture.marslandscapes.view.notes

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.FragmentNotesBinding
import space.picture.marslandscapes.databinding.PictureOfTheDayStartFragmentBinding
import space.picture.marslandscapes.databinding.SettingsFragmentBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesFragment : Fragment() {

    private var isExpanded = false
    private var count: Int = 0
    private val myLocale: Locale = Locale("ru", "RU")
    private var data: MutableList<ItemNotes> = arrayListOf()

    private val notesAdapter = NotesAdapter(data, count, myLocale)

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
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFabs()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerNotesView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerNotesView.adapter = notesAdapter
    }

    private fun showFabs() {
        binding.recyclerNotesFAB.setOnClickListener {
            if (isExpanded) {
                isExpanded = false
                ObjectAnimator.ofFloat(binding.recyclerNotesFAB, "rotation", 0f).start()
                binding.plusNote.visibility = View.GONE
                binding.plusCake.visibility = View.GONE
                binding.plusNote.isClickable = false
                binding.plusCake.isClickable = false

            } else {
                isExpanded = true
                ObjectAnimator.ofFloat(binding.recyclerNotesFAB, "rotation", 135f).start()
                binding.plusNote.visibility = View.VISIBLE
                binding.plusCake.visibility = View.VISIBLE
                binding.plusNote.isClickable = true
                binding.plusCake.isClickable = true

            }
        }
        setupFabs()
    }

    private fun setupFabs(){
        binding.plusNote.setOnClickListener { notesAdapter.appendItemNote() }
        binding.plusCake.setOnClickListener { notesAdapter.appendItemCake() }
    }

    companion object {
        fun newInstance() = NotesFragment()
    }
}
