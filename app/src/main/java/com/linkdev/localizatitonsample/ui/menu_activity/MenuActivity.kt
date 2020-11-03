package com.linkdev.localizatitonsample.ui.menu_activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.fragments.NewsFragment
import com.linkdev.localizatitonsample.ui.news.OnAdapterNewsInteraction
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.tool_bar_layout.*


class MenuActivity : AppCompatActivity(), OnAdapterNewsInteraction {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment_toolbar)
        UIUtils.setToolbar(this, toolBar, getString(R.string.news), false)
        replaceFragment(R.id.fragmentContainer, NewsFragment.newInstance(), NewsFragment.TAG)
    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment?,
        fragmentTag: String?
    ) {
        if (fragment == null || fragmentTag == null) {
            return
        }
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(containerViewId, fragment, fragmentTag).commit()
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


    // TODO: attach configuration context to [attachBaseContext] of consumer activity to notify it with updated resources
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {

    }
}
