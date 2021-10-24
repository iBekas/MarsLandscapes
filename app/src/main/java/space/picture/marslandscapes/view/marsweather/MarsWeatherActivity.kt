package space.picture.marslandscapes.view.marsweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.picture.marslandscapes.databinding.MarsWeatherActivityBinding

class MarsWeatherActivity : AppCompatActivity() {

    lateinit var binding: MarsWeatherActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MarsWeatherActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}