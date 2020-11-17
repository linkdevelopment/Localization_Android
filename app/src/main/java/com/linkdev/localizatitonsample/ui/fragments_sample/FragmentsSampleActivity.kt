package com.linkdev.localizatitonsample.ui.fragments_sample

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.common.news.NewsFragment
import com.linkdev.localizatitonsample.ui.common.news.OnAdapterNewsInteraction
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.tool_bar_layout.*

class FragmentsSampleActivity : AppCompatActivity(), OnAdapterNewsInteraction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment_toolbar)
        setListeners()
        UIUtils.setToolbar(this, toolBar, getString(R.string.app_name), false)
        replaceFragment(R.id.fragmentContainer, NewsFragment.newInstance(), NewsFragment.TAG)
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
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(containerViewId, fragment, fragmentTag).commit()
    }


    override fun attachBaseContext(newBase: Context) {
        // TODO:Call [Localization.onAttach()] to attach configuration context to [attachBaseContext] of consumer activity
        //  to notify it with updated resources
        super.attachBaseContext(Localization.onAttach(this, newBase))
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {
        replaceFragment(
            R.id.fragmentContainer,
            NewsDetailsFragment.newInstance(newsModel),
            NewsDetailsFragment.TAG
        )

    }
}