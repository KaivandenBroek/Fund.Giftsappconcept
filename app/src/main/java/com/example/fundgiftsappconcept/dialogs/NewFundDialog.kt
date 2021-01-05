package com.example.fundgiftsappconcept.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.dialog_new_fund.*

class NewFundDialog : DialogFragment() {

    private val fundViewModel: FundViewmodel by viewModels()

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
            saveFund()
            val title = tfNewFundTitle.text
            Toast.makeText(context, "$title created!", Toast.LENGTH_LONG).show()
            dismiss()
        }
    }

    private fun saveFund() {
        // takes the text from the amount field, makes it a string and then splits into a double
        val amount = tfAmount.text.toString().split(" ")[0].toDouble()
        val fund = Fund(
            id = null,
            fundName = tfNewFundTitle.text.toString(),
            creatorId = "5ff3441ad2bcbf6407b7f4e3",
            currentAmount = 0.0,
            fullAmount = amount
                )
        fundViewModel.insertFund(fund)
    }
}