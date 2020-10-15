package com.linkdev.localization.utils

import android.content.res.Configuration
import android.os.Build
import java.util.*

@Suppress("DEPRECATION")
internal fun Configuration.getLocaleCompat(): Locale {
    return if (isAtLeastSdkVersion(Build.VERSION_CODES.N)) locales.get(0) else locale
}

internal fun isAtLeastSdkVersion(versionCode: Int): Boolean {
    return Build.VERSION.SDK_INT >= versionCode
}


