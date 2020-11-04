package com.linkdev.localizatitonsample.ui.activities_sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.fragments_sample.NewsDetailsFragment
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news_details.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*


class NewsDetailsActivity : AppCompatActivity() {
    companion object {
        const val NEWS_MODEL_TAG = "NEWS_MODEL_TAG"
        fun startActivity(context: Context, newsModel: NewsModel) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(NEWS_MODEL_TAG, newsModel)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        UIUtils.setToolbar(this, toolBar, getString(R.string.newsDetails), false)
        loadNewsDetails()
    }

    private fun loadNewsDetails() {
        val newsModel: NewsModel =
            intent?.extras?.getParcelable(NewsDetailsFragment.NEWS_MODEL_TAG) ?: return
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(this, newsModel.imgIDRes))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.news_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.arabic -> {
                changeLang(Locales.Arabic)

                return true
            }

            R.id.english -> {
                changeLang(Locales.English)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRestart(
            this,
            newLocale,
            NewsActivity::class.java,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
    }

    override fun attachBaseContext(newBase: Context) {
        // TODO:Call [Localization.onAttach()] to attach configuration context to [attachBaseContext] of consumer activity
        //  to notify it with updated resources
        super.attachBaseContext(Localization.onAttach(newBase))
    }


}
