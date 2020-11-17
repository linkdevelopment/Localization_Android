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
