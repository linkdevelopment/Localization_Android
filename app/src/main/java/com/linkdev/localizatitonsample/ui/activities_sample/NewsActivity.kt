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
package com.linkdev.localizatitonsample.ui.activities_sample

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.common.news.NewsAdapter
import com.linkdev.localizatitonsample.ui.common.news.OnAdapterNewsInteraction
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*


class NewsActivity : AppCompatActivity(), OnAdapterNewsInteraction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        UIUtils.setToolbar(this, toolBar, getString(R.string.news), false)
        loadnewsList()
    }

    private fun loadnewsList() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        val newsAdapter = NewsAdapter(this, UIUtils.createNewsList(), this)
        rvNews.adapter = newsAdapter
    }

    override fun attachBaseContext(newBase: Context) {
        // TODO:Call [Localization.onAttach()] to attach configuration context to [attachBaseContext] of consumer activity
        //  to notify it with updated resources
        super.attachBaseContext(Localization.onAttach(this, newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {
        NewsDetailsActivity.startActivity(this, newsModel)
    }
}
