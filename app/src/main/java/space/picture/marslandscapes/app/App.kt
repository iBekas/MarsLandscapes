package space.picture.marslandscapes.app


import android.app.Application
import space.picture.marslandscapes.R

class App : Application() {

    var currentTheme = R.style.AppTheme

    override fun onCreate() {
        super.onCreate()
//        instance = this
    }

    companion object {
        fun newInstance() = App()
//        lateinit var instance: App
    }
}