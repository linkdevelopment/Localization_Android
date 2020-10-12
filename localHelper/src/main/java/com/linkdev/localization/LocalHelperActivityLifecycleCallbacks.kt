package com.linkdev.localization

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

class LocalHelperActivityLifecycleCallbacks(
    private val callback: (Activity) -> Unit
) : ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        callback.invoke(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }
}
