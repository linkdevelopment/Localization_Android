package com.linkdev.localizatitonsample.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.activities_sample.NewsActivity
import com.linkdev.localizatitonsample.ui.fragments_sample.FragmentsSampleActivity
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
import com.linkdev.localizatitonsample.ui.one_activity.MenuActivity
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        UIUtils.setToolbar(this, toolBar, getString(R.string.app_name), false)

    }

    private fun setListeners() {
        btnNavSample.setOnClickListener {
            navigateToNavigationSample()
        }
        btnActivitiesSample.setOnClickListener { navigateToActivitiesSample() }
        btnFragmentsSample.setOnClickListener { navigateToFragmentsSample() }
        btnMenuSample.setOnClickListener { navigateToMenuSample() }
    }

    private fun navigateToNavigationSample() {
        startActivity(
            Intent(this, NavigationActivity::class.java)
        )
        finish()
    }

    private fun navigateToActivitiesSample() {
        startActivity(
            Intent(this, NewsActivity::class.java)
        )
        finish()
    }

    private fun navigateToFragmentsSample() {
        startActivity(
            Intent(this, FragmentsSampleActivity::class.java)
        )
        finish()
    }

    private fun navigateToMenuSample() {
        startActivity(
            Intent(this, MenuActivity::class.java)
        )
        finish()
    }
}
