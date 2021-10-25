package space.picture.marslandscapes.view.roverphoto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.MarsRoverPhotoActivityBinding
import space.picture.marslandscapes.util.getAppTheme

class MarsRoverPhotoActivity : AppCompatActivity() {

    lateinit var binding: MarsRoverPhotoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(this))
        binding = MarsRoverPhotoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
        with(binding){
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = "OBJECT ${(position + 1)}"
            }.attach()

            tabLayout.getTabAt(0)?.customView =
                layoutInflater.inflate(R.layout.tablayout_mars_front_cam, null)
            tabLayout.getTabAt(1)?.customView =
                layoutInflater.inflate(R.layout.tablayout_mars_rear_cam, null)
            tabLayout.getTabAt(2)?.customView =
                layoutInflater.inflate(R.layout.tablayout_mars_panoramic_cam, null)
        }
    }
}