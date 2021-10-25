package space.picture.marslandscapes.view.roverphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import space.picture.marslandscapes.databinding.RearMarsCamFragmentBinding
import space.picture.marslandscapes.viewmodel.MarsRoverPhotoViewModel

class RearMarsCamFragment : Fragment() {

    private val viewModel: MarsRoverPhotoViewModel by lazy {
        ViewModelProvider(this).get(MarsRoverPhotoViewModel::class.java)
    }

    private var _binding: RearMarsCamFragmentBinding? = null
    private val binding: RearMarsCamFragmentBinding
        get(): RearMarsCamFragmentBinding {
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
        _binding = RearMarsCamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}