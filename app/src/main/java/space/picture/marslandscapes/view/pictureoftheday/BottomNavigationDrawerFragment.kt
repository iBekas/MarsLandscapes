package space.picture.marslandscapes.view.pictureoftheday

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.BottomNavigationLayoutBinding
import space.picture.marslandscapes.util.toast
import space.picture.marslandscapes.view.roverphoto.MarsRoverPhotoActivity

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {


    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { item->
            when (item.itemId) {
                R.id.app_bar_settings -> toast("В работе")
                R.id.app_bar_rover -> startActivity(Intent(context, MarsRoverPhotoActivity::class.java))
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    companion object {
        fun newInstance() = BottomNavigationDrawerFragment()
    }
}