package com.linkdev.localizatitonsample.utils

import android.content.Context
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel

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

    fun createNewsList(): ArrayList<NewsModel> {
        val newsList: ArrayList<NewsModel> = arrayListOf()
        val newsModel = NewsModel(
            R.string.introductionToActivities,
            R.string.introductionToActivitiesContent,
            R.drawable.ic_android_lollipop
        )
        newsList.add(newsModel)
        val newsModel1 = NewsModel(
            R.string.understandTheActivityLifecycle,
            R.string.understandTheActivityLifecycleContent,
            R.drawable.ic_android_device
        )
        newsList.add(newsModel1)

        val newsModel2 = NewsModel(
            R.string.handleActivityStateChanges,
            R.string.handleActivityStateChangesContent,
            R.drawable.ic_android_google_security
        )
        newsList.add(newsModel2)

        val newsModel3 = NewsModel(
            R.string.testYourAppActivities,
            R.string.testYourAppActivitiesContent,
            R.drawable.ic_android_store
        )
        newsList.add(newsModel3)

        val newsModel4 = NewsModel(
            R.string.understandTasksAndBackStack,
            R.string.understandTasksAndBackStackContent,
            R.drawable.ic_android_lollipop
        )
        newsList.add(newsModel4)


        return newsList
    }

    fun setDrawableEndText(textView: TextView, @DrawableRes drawableID: Int) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableID, 0)

    }

    fun clearDrawable(textView: TextView) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
    }
}