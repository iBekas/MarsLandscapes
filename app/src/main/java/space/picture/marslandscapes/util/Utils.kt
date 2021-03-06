package space.picture.marslandscapes.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import space.picture.marslandscapes.R
import space.picture.marslandscapes.view.settings.SettingsFragment
import java.text.SimpleDateFormat
import java.util.*

fun getAppTheme(activity: Activity): Int {
    val sharedPref: SharedPreferences = activity.getSharedPreferences(
        SettingsFragment.THEME_SHARED_PREFERENCE,
        Context.MODE_PRIVATE
    )
    return sharedPref.getInt(SettingsFragment.THEME, R.style.AppTheme)
}

fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}

fun getDaysAgo(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return simpleDateFormat.format(calendar.time)
}

