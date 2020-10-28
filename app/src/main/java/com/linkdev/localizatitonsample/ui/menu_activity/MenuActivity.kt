package com.linkdev.localizatitonsample.ui.menu_activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.news.NewsAdapter
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*


class MenuActivity : AppCompatActivity(), NewsAdapter.OnAdapterNewsInteraction {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        UIUtils.setToolbar(this, toolBar, getString(R.string.news), false)
        updateNewsList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuMore -> {
                Localization.reverseLangAndRestart(this, MenuActivity::class.java)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateNewsList() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        val newsAdapter = NewsAdapter(this, UIUtils.createNewsList(), this, false)
        rvNews.adapter = newsAdapter
    }

    //todo here we need to pass new context to attachBaseContext that has been created by
    // configuration context with new locale
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {

    }
}
