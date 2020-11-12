package com.linkdev.localizatitonsample.ui.navigation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.linkdev.localization.Localization
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.Constants
import com.linkdev.localizatitonsample.utils.Constants.Extras.NO_ACTION
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setupNavigation()
        handleIntent()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.navHostFragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    // TODO: 10/18/2020 receive arguments here that has been sent to {Localization} component and start navigation
    private fun handleIntent() {
        val actionId = intent.getIntExtra(
            Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
            NO_ACTION
        )
        val deepLink = intent.getStringExtra(
            Constants.Extras.CHANGE_LANGUAGE_REDIRECTION
        )
        when {
            actionId != NO_ACTION -> {
                navigateTo(actionId)
            }
            !deepLink.isNullOrBlank() -> {
                navigateTo(deepLink.toUri())

            }
        }
    }

    fun navigateTo(
        actionId: Int
    ) {
        val navController = findNavController(R.id.navHostFragment)
        navController
            .navigate(actionId, null, null)

    }

    fun navigateTo(deepLink: Uri) {
        findNavController(R.id.navHostFragment).navigate(deepLink)
    }


    override fun attachBaseContext(newBase: Context) {
        // TODO:Call [Localization.onAttach()] to attach configuration context to [attachBaseContext] of consumer activity
        //  to notify it with updated resources
        super.attachBaseContext(Localization.onAttach(newBase))
    }
}
