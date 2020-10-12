package com.linkdev.localizationHelper.uitils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.linkdev.localizationHelper.R


interface IToolbar {
    var mToolbar: Toolbar?

    fun setToolbar(
        context: Context, title: String? = null, setHomeAsUpEnabled: Boolean = true,
        navigationIconDrawable: Int = R.drawable.ic_back
    ) {
        if (mToolbar == null)
            return

        if (navigationIconDrawable != emptyNavigationIcon)
            mToolbar?.setNavigationIcon(navigationIconDrawable)

        mToolbar?.title = title

        (context as AppCompatActivity).setSupportActionBar(mToolbar)

        context.supportActionBar?.setDisplayHomeAsUpEnabled(setHomeAsUpEnabled)
    }

    fun setToolbarNavigationIcon(iconId: Int): Toolbar? {
        mToolbar?.setNavigationIcon(iconId)
        return mToolbar
    }

    fun setToolbarTitle(title: String): Toolbar? {
        mToolbar?.title = title
        return mToolbar
    }

    fun setToolbarSubTitle(subtitle: String): Toolbar? {
        mToolbar?.subtitle = subtitle
        return mToolbar
    }

    private val emptyNavigationIcon
        get() = -1
}