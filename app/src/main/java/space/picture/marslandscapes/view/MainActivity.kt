package space.picture.marslandscapes.view

import android.os.Bundle
import space.picture.marslandscapes.databinding.ActivityMainBinding
import space.picture.marslandscapes.util.App
import space.picture.marslandscapes.util.getAppTheme

class MainActivity : FragmentChoice() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(this))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getFragmentByFragmentId(App.fragmentId)
    }
}