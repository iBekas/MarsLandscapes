package space.picture.marslandscapes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAnim()
    }

    private fun setAnim() {
        binding.imageView.animate().rotationBy(-1000f).setInterpolator(LinearInterpolator()).duration = 3000
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
