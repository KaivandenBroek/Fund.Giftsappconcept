package com.example.fundgiftsappconcept.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val fundViewModel: FundViewmodel by viewModels()
    private val clientId : String = "nwkd9bwmzeq5u51ffa40hzv9kt6hja"
    private val clientSecret : String = "37l1fo43gu95ghj7govhfhzk9tvtih"
    private val redirectUrl : String = "https://10.0.2.2/fundgifts/callback"

    private val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://id.twitch.tv/oauth2/authorize?" +
            "client_id=" + clientId
            + "&redirect_uri=" + redirectUrl
            + "&response_type=code" +
            "&scope=viewing_activity_read" +
            "&state=c3ab8aa609ea11e793ae92361f002671"))

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSadLogin.setOnClickListener {
            findNavController().navigate(R.id.codedLoginFragment)
        }

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            //userViewModel.setUser()

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No application can handle this request."
                        + " Please install a webbrowser", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

            //findNavController().navigate(R.id.homeFragment)
        }
    }

    @Override
    override fun onResume() {
        super.onResume()

        val uri: Uri? = intent.data
        Log.v("REDIRECT URL CODE: ", uri.toString())

        if(uri != null && uri.toString().startsWith(redirectUrl)) {
            var code: String = uri.getQueryParameter("code").toString()
            // get auth code

            Toast.makeText(context, "SUCCES", Toast.LENGTH_LONG).show()
        }
    }

    // Checks if twitch is already installed
    private fun isPackageInstalled(packageName: String, context: Context): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}