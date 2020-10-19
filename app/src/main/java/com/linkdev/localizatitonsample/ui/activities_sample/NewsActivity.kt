package com.linkdev.localizatitonsample.ui.activities_sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        UIUtils.setToolbar(this, toolBar, getString(R.string.news), false)
        setListeners()

    }

    private fun setListeners() {
        btnNewsDetails.setOnClickListener {
            navigateToNewsDetails()
        }
    }

    private fun navigateToNewsDetails() {
        startActivity(Intent(this, NewsDetailsActivity::class.java))
    }

}
