package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fundgiftsappconcept.R

class FundsFragment : Fragment(R.layout.fragment_funds) {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        return inflater.inflate(R.layout.fragment_funds, container, false)
    }
}