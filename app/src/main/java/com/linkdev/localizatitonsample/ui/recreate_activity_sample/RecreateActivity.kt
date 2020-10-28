package com.linkdev.localizatitonsample.ui.recreate_activity_sample

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.news.NewsAdapter
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*


class RecreateActivity : AppCompatActivity(), NewsAdapter.OnAdapterNewsInteraction {


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
                changeLang(getReversedLocale())
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

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRecreate(
            this,
            newLocale
        )
    }

    private fun getReversedLocale(): Locale {
        return if (LocalizationPrefsDataSource.init(this).getLanguage() == Locales.Arabic.language
        ) {
            Locales.English
        } else {
            Locales.Arabic
        }
    }

    //todo here we need to pass new context to attachBaseContext that has been created by
    // configuration context with new locale
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {

    }
}
