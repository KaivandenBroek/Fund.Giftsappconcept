package com.example.fundgiftsappconcept.ui

import androidx.navigation.fragment.findNavController

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fundgiftsappconcept.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : AppCompatActivity() {
    private lateinit var navController: NavController
    private val clientId : String = "nwkd9bwmzeq5u51ffa40hzv9kt6hja"
    private val clientSecret : String = "37l1fo43gu95ghj7govhfhzk9tvtih"
    private val redirectUrl : String = "https://localhost/fundgifts/callback"

    @Override
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_auth)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        btnSucces.setOnClickListener {

            navController.navigate(R.id.homeFragment)
        }
    }

    @Override
    override fun onResume() {
        super.onResume()

        val uri: Uri? = intent.data

        if(uri != null && uri.toString().startsWith(redirectUrl)) {
            var code: String = uri.getQueryParameter("code").toString()
            // get auth code

            Toast.makeText(applicationContext, "SUCCES", Toast.LENGTH_LONG).show()
        }
    }

}