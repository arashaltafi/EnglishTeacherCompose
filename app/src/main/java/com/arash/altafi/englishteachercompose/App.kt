package com.arash.altafi.englishteachercompose

import androidx.hilt.work.HiltWorkerFactory
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import androidx.work.Configuration
import com.arash.altafi.englishteachercompose.utils.language.LocaleUtils
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        LocaleUtils.Companion.setLocale(this)
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

    //config language for all application
    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.Companion.setLocale(this)
    }
}