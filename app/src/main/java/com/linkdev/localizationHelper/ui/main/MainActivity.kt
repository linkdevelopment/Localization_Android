package com.linkdev.gafi.eservices.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.linkdev.localizationHelper.R
import com.linkdev.localizationHelper.uitils.Constants
import com.linkdev.localizationHelper.uitils.Constants.Extras.NO_ACTION
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
        handleIntent()
    }

    private fun handleIntent() {
        val actionId = intent.getIntExtra(
            Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
            NO_ACTION
        )
        if (actionId != NO_ACTION) {
            navController.navigate(actionId)
        }
    }


}
