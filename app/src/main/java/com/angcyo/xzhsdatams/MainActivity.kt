package com.angcyo.xzhsdatams

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import com.angcyo.library.utils.L
import com.angcyo.uiview.RCrashHandler
import com.angcyo.uiview.base.UIBaseView
import com.angcyo.uiview.base.UILayoutActivity
import com.angcyo.xzhsdatams.iview.MainUIView

class MainActivity : UILayoutActivity() {

    override fun initScreenOrientation() {
//        if (BuildConfig.DEBUG) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
//        }
    }

    override fun onLoadView(intent: Intent) {
        checkPermissions()
        startIView(MainUIView().setEnableClipMode(UIBaseView.ClipMode.CLIP_START))

        RCrashHandler.checkCrash(mLayout)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        L.e("call: onConfigurationChanged -> $newConfig")
    }
}
