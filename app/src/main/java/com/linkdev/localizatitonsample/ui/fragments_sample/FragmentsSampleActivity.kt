package com.linkdev.localizatitonsample.ui.fragments_sample

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.tool_bar_layout.*

class FragmentsSampleActivity : AppCompatActivity() {
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

}