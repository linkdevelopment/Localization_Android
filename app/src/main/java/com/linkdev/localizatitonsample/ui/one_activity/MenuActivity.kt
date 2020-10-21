package com.linkdev.localizatitonsample.ui.one_activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.main.MainActivity
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.langs_layout.*
import kotlinx.android.synthetic.main.tool_bar_layout.*


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        UIUtils.setToolbar(this, toolBar, getString(R.string.singleActivity), false)
        setListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuMore -> {
                Localization.reverseLangAndRestart(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            Localization.setLocaleAndRecreatet(
                this,
                Locales.Arabic
            )

        }
        btnEnglishLang.setOnClickListener {
            Localization.setLocaleAndRecreatet(
                this,
                Locales.English
            )
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }
}
