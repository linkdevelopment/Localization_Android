package com.linkdev.localizatitonsample.ui.activities_sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.langs_layout.*
import kotlinx.android.synthetic.main.tool_bar_layout.*


class NewsDetailsActivity : AppCompatActivity() {
    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setListeners()
        UIUtils.setToolbar(this, toolBar, getString(R.string.newsDetails), false)

    }

    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                this,
                Locales.Arabic,
                NewsActivity::class.java,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )

        }
        btnEnglishLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                this,
                Locales.English,
                NewsActivity::class.java,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        }
    }


}
