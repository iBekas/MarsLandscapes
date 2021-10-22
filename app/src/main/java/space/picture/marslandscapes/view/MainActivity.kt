package space.picture.marslandscapes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import space.picture.marslandscapes.R
import space.picture.marslandscapes.app.App
import space.picture.marslandscapes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val app: App = App.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }

    override fun onRestart() {
        super.onRestart()
        setTheme(app.currentTheme)
    }

    fun changeTheme(theme: Int) {
        app.currentTheme = theme
        this.recreate()
    }
}