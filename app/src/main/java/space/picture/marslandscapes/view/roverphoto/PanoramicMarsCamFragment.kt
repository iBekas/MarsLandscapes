package space.picture.marslandscapes.view.roverphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import space.picture.marslandscapes.databinding.PanoramicMarsCamFragmentBinding
import space.picture.marslandscapes.viewmodel.MarsRoverPhotoViewModel

class PanoramicMarsCamFragment : Fragment() {

    private val viewModel: MarsRoverPhotoViewModel by lazy {
        ViewModelProvider(this).get(MarsRoverPhotoViewModel::class.java)
    }

    private var _binding: PanoramicMarsCamFragmentBinding? = null
    private val binding: PanoramicMarsCamFragmentBinding
        get(): PanoramicMarsCamFragmentBinding {
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
        _binding = PanoramicMarsCamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}