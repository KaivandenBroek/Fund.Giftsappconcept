package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.text.TextUtils
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
import kotlinx.android.synthetic.main.fragment_coded_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private val fundViewModel: FundViewmodel by viewModels()
    private val minmalChars: Int = 2;

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

            when {
                TextUtils.isEmpty(tfNewUserName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            activity?.baseContext,
                            "Username required!",
                            Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.getTrimmedLength((tfNewUserName.text.toString())) < minmalChars ->  {
                    Toast.makeText(
                            activity?.baseContext,
                            "Username should be atleast 3 characters long",
                            Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(tfNewUserMail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            activity?.baseContext,
                            "mail required!",
                            Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(tfNewUserAge.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            activity?.baseContext,
                            "age required!",
                            Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
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
    }
}