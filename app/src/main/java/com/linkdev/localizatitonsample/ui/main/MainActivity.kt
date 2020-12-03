/**
Copyright (C) 2020 Link Development

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **/
package com.linkdev.localizatitonsample.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.activities_sample.NewsActivity
import com.linkdev.localizatitonsample.ui.fragments_sample.FragmentsSampleActivity
import com.linkdev.localizatitonsample.ui.menu_activity.MenuActivity
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
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

    }

    private fun navigateToActivitiesSample() {
        startActivity(
            Intent(this, NewsActivity::class.java)
        )
    }

    private fun navigateToFragmentsSample() {
        startActivity(
            Intent(this, FragmentsSampleActivity::class.java)
        )
    }

    private fun navigateToMenuSample() {
        startActivity(
            Intent(this, MenuActivity::class.java)
        )
    }


}
