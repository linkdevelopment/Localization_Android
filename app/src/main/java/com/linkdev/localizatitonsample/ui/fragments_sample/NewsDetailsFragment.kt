package com.linkdev.localizatitonsample.ui.fragments_sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.activities_sample.NewsActivity
import com.linkdev.localizatitonsample.utils.Constants
import kotlinx.android.synthetic.main.langs_layout.view.*


class NewsDetailsFragment : Fragment() {
    companion object {
        const val TAG = "NewsDetailsFragment"
        fun newInstance(): NewsDetailsFragment {
            return NewsDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_news_details, container, false)
        initializeViews(rootView)
        setListeners(rootView)
        return rootView
    }

    private fun setListeners(rootView: View) {
        rootView.btnArabicLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.Arabic,
                FragmentsSampleActivity::class.java,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )

        }
        rootView.btnEnglishLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.English,
                FragmentsSampleActivity::class.java,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        }
    }


    private fun initializeViews(rootView: View) {

    }

}