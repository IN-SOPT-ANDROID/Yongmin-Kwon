package org.sopt.sample

import android.app.Application
import org.sopt.sample.data.local.MySharedPreferences

class InSoptAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
}
