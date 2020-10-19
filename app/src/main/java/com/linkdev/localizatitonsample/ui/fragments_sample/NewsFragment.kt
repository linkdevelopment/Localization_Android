package com.linkdev.localizatitonsample.ui.fragments_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.linkdev.localizatitonsample.R
import kotlinx.android.synthetic.main.fragment_news.view.*


class NewsFragment : Fragment() {
    companion object {
        const val TAG = "NewsFragment"
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_news, container, false)
        initializeViews(rootView)
        setListeners(rootView)
        return rootView
    }

    private fun setListeners(rootView: View) {
        rootView.btnNewsDetails.setOnClickListener {
            navigateToNewsDetails()
            rootView.btnNewsDetails.visibility = View.GONE
        }

    }


    private fun navigateToNewsDetails() {

        replaceFragment(
            R.id.frmlContainer,
            NewsDetailsFragment.newInstance(),
            NewsDetailsFragment.TAG
        )
    }

    private fun initializeViews(rootView: View) {

    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment?,
        fragmentTag: String?
    ) {
        if (fragment == null || fragmentTag == null) {
            return
        }

        childFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .commit();

    }
}