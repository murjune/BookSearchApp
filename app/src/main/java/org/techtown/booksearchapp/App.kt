package org.techtown.booksearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.techtown.booksearchapp.util.TimberDebugTree
import timber.log.Timber

@HiltAndroidApp
// 앱의 수명 주기에 연결된 컨테이너를 추가
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(TimberDebugTree())
    }
}
