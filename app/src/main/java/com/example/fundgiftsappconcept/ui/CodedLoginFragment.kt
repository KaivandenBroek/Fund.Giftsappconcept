package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import kotlinx.coroutines.*
import retrofit2.Response

class CodedLoginFragment : Fragment() {
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

        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        btnCodedLogin.setOnClickListener {
            val username = tfCodedLoginUsername.text.toString().trim { it <= ' ' }
            val title = tfCodedLoginUsername.text.toString()

            when {
                TextUtils.isEmpty(tfCodedLoginUsername.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            activity?.baseContext,
                            "Username required!",
                            Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val result = fundViewModel.getUserByUsername(username)
                            auth(result, title)
                        } catch (ex: Exception) {
                            Log.e("login error", ex.toString())
                        }
                    }
                }
            }
        }
    }

    private suspend fun auth(res: Response<User>, title: String) {
        var badName = true

        withContext(Dispatchers.Main) {
            if (res.isSuccessful) {
                res.body()?.let { data ->
                    fundViewModel.setCurrentUser(User(
                            id = data.id,
                            name = data.name,
                            age = data.age,
                            email = data.email
                    ))
                    Toast.makeText(context, "Logged in as $title", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.homeFragment)
                    badName = false
                }
                if (badName) Toast.makeText(context, "$title is not a username!", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "$title is not a username!", Toast.LENGTH_LONG).show()
            }
        }
    }
}