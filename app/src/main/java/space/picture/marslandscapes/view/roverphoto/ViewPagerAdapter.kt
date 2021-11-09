package space.picture.marslandscapes.view.roverphoto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val FRONT =0
private const val REAL =1
private const val PANORAMIC =2

class ViewPagerAdapter(private val fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(FrontMarsCamFragment(), RearMarsCamFragment(), PanoramicMarsCamFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->fragments[FRONT]
            1->fragments[REAL]
            2->fragments[PANORAMIC]
            else ->fragments[FRONT]
        }
    }

}