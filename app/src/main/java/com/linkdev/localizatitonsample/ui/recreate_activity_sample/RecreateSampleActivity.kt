package com.linkdev.localizatitonsample.ui.recreate_activity_sample

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.common.news.NewsAdapter
import com.linkdev.localizatitonsample.ui.common.news.OnAdapterNewsInteraction
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*


class RecreateSampleActivity : AppCompatActivity(), OnAdapterNewsInteraction {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recreate_sample)
        UIUtils.setToolbar(this, toolBar, getString(R.string.news), false)
        loadnewsList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuMore -> {
                changeLang(getReversedLocale())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun loadnewsList() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        val newsAdapter = NewsAdapter(this, UIUtils.createNewsList(), this)
        rvNews.adapter = newsAdapter
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRecreate(
            this,
            newLocale
        )
    }

    private fun getReversedLocale(): Locale {
        return if (Localization.getLanguage() == Locales.Arabic.language
        ) {
            Locales.English
        } else {
            Locales.Arabic
        }
    }

    override fun attachBaseContext(newBase: Context) {
        // TODO:Call [Localization.onAttach()] to attach configuration context to [attachBaseContext] of consumer activity
        //  to notify it with updated resources
        super.attachBaseContext(Localization.onAttach(newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {

    }
}
