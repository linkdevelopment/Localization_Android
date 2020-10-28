package com.linkdev.localizatitonsample.ui.fragments_sample

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.fragments.NewsDetailsFragment
import com.linkdev.localizatitonsample.ui.fragments.NewsFragment
import com.linkdev.localizatitonsample.ui.news.NewsAdapter
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.tool_bar_layout.*

class FragmentsSampleActivity : AppCompatActivity(), NewsAdapter.OnAdapterNewsInteraction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)
        setListeners()
        UIUtils.setToolbar(this, toolBar, getString(R.string.app_name), false)
        replaceFragment(R.id.frmlContainer, NewsFragment.newInstance(), NewsFragment.TAG)
    }

    private fun setListeners() {

    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment?,
        fragmentTag: String?
    ) {
        if (fragment == null || fragmentTag == null) {
            return
        }
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment, fragmentTag).commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(NewsFragment.TAG)
        if (fragment == null ) {
            replaceFragment(R.id.frmlContainer, NewsFragment.newInstance(), NewsFragment.TAG)
        } else {
            super.onBackPressed()
        }
    }

    //todo here we need to pass new context to attachBaseContext that has been created by
    // configuration context with new locale
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {
        replaceFragment(R.id.frmlContainer, NewsDetailsFragment.newInstance(newsModel), NewsDetailsFragment.TAG)

    }
}