package sheridan.sullnich.exam

import android.app.Application
import sheridan.sullnich.exam.data.AppContainer
import sheridan.sullnich.exam.data.DefaultAppContainer

class CollegeApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}