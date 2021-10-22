package space.picture.marslandscapes.view


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import space.picture.marslandscapes.R


abstract class FragmentChoice : AppCompatActivity() {

    companion object {
        const val PICTURE_FRAGMENT = 0
        const val SETTINGS_FRAGMENT = 1
    }

    fun getFragmentByFragmentId(fragment: Int): Int {
        return when (fragment) {
            PICTURE_FRAGMENT -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).addToBackStack("").commit()
            SETTINGS_FRAGMENT -> supportFragmentManager.beginTransaction()
                .detach(SettingsFragment.newInstance())
                .attach(SettingsFragment.newInstance())
                .commit()

            else -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }

}