package com.linkdev.localization.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

object LaunchUtils {

    fun <T : Activity> startActivity(
        activity: Activity,
        activityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        val intent = Intent(activity, activityClass)
        if (flags != null) {
            intent.flags = flags
        }
        if (bundle != null)
            intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.finish()
    }

}