package com.jam.utils.myapplication

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by hejiaming on 2018/5/18.
 * @desciption:
 */
class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}