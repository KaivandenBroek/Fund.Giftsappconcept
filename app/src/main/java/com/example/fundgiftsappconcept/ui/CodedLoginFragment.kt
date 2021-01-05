package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_coded_login.*

class CodedLoginFragment : Fragment(){
    private val fundViewModel: FundViewmodel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coded_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCodedLogin.setOnClickListener {
            if(tfCodedLoginUsername.text.isNullOrEmpty()){
                Toast.makeText(context, "Username required!", Toast.LENGTH_LONG).show()
            } else {
                findNavController().navigate(R.id.homeFragment)
//                if (findAndSetUser(tfCodedLoginUsername.text.toString())) {
//                    findNavController().navigate(R.id.homeFragment)
//                } else {
//                    val title = tfCodedLoginUsername.text.toString()
//                    Toast.makeText(context, "$title is not a username!", Toast.LENGTH_LONG).show()
//                }
            }

        }
    }

    private fun findAndSetUser(username: String): Boolean {
        val user = fundViewModel.getUserByUsername(username)
        return user != null
    }

}