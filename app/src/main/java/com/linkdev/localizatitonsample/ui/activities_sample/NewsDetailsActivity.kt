package com.linkdev.localizatitonsample.ui.activities_sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.fragments.NewsDetailsFragment
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.langs_layout.*
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
        setListeners()
        UIUtils.setToolbar(this, toolBar, getString(R.string.newsDetails), false)
        setNewsDetails()
    }

    private fun setNewsDetails() {
        val newsModel: NewsModel = intent?.extras?.getParcelable(NewsDetailsFragment.NEWS_MODEL_TAG) ?: return
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(this, newsModel.imgIDRes))
    }

    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            changeLang(Locales.Arabic)
        }

        btnEnglishLang.setOnClickListener {
            changeLang(Locales.English)
        }
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRestart(
            this,
            newLocale,
            NewsActivity::class.java,
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
    }

    //todo here we need to pass new context to attachBaseContext that has been created by
    // configuration context with new locale
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }


}
