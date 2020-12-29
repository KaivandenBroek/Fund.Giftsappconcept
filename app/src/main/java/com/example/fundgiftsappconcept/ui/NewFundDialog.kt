package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.fundgiftsappconcept.R
import kotlinx.android.synthetic.main.dialog_new_fund.*
import kotlinx.android.synthetic.main.fragment_home.*

class NewFundDialog: DialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_new_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnConfirm.setOnClickListener {
            val title = tfNewFundTitle.text
            Toast.makeText(context, "$title created!", Toast.LENGTH_LONG).show()

            dismiss()
        }
    }
}