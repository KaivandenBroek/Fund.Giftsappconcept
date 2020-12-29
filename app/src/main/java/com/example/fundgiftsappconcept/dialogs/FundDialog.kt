package com.example.fundgiftsappconcept.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.fundgiftsappconcept.R
import kotlinx.android.synthetic.main.dialog_fund.*

class FundDialog: DialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slider.valueTo = 80.0F
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnConfirm.setOnClickListener {
            val title = "no data yet"
            Toast.makeText(context, "$title funded!", Toast.LENGTH_LONG).show()

            dismiss()
        }
    }
}