package com.linkdev.localizatitonsample.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.linkdev.localizatitonsample.R

object UIUtils {

    fun setToolbar(
        context: Context, toolBar: Toolbar?,
        title: String? = null, setHomeAsUpEnabled: Boolean = true,
        navigationIconDrawable: Int = R.drawable.ic_back
    ) {
        if (navigationIconDrawable != -1)
            toolBar?.setNavigationIcon(navigationIconDrawable)
        toolBar?.title = title
        (context as AppCompatActivity).setSupportActionBar(toolBar)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(setHomeAsUpEnabled)
    }
}