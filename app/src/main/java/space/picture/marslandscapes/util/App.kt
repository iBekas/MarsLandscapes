package space.picture.marslandscapes.util

import android.app.Application

class App : Application() {



    override fun onCreate() {
        super.onCreate()
//        instance = this
    }

    companion object {
        var fragmentId = 0
        fun newInstance() = App()
//        lateinit var instance: App
    }
}
