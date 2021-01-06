package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.model.User
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private val fundViewModel: FundViewmodel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCancel.setOnClickListener {
            findNavController().navigate(R.id.codedLoginFragment)
        }
        btnCreate.setOnClickListener {
            val user = User(
                    id = null,
                    name = tfNewUserName.text.toString(),
                    email = tfNewUserMail.text.toString(),
                    age = tfNewUserAge.text.toString()
            )
            fundViewModel.insertUser(user)
            findNavController().navigate(R.id.codedLoginFragment)
            Toast.makeText(context, "${user.name} created!", Toast.LENGTH_LONG).show()
        }

    }
}