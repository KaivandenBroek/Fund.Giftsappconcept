package com.example.fundgiftsappconcept.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.adapters.FundAdapter
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.dialog_fund.*
import okhttp3.internal.notify

class FundDialog(fund: Fund, fundAdapter: FundAdapter) : DialogFragment() {

    private val fundViewModel: FundViewmodel by viewModels()
    private var fundData = fund
    private val adapter = fundAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // sets max fundable amount so noone can over-fund
        slider.valueTo = (fundData.fullAmount-fundData.currentAmount).toFloat()

        btnCancel.setOnClickListener { dismiss() }

        btnConfirm.setOnClickListener {

            val title = fundData.fundName
            val amount = slider.value.toDouble()
            Toast.makeText(context, "$amount funded to $title!", Toast.LENGTH_LONG).show()

            // to update the fund i have to replace it
            val updatedFund = Fund(
                    id = fundData.id,
                    fundName = fundData.fundName,
                    creatorId = fundData.creatorId,
                    fullAmount = fundData.fullAmount,
                    currentAmount = fundData.currentAmount + amount
            )

            Log.v("FUND: ", updatedFund.toString())
            updateFund(updatedFund)
            adapter.arrayList.remove(fundData)
            adapter.arrayList.add(updatedFund)
            adapter.notifyDataSetChanged()
            dismiss()
        }
    }

    private fun updateFund(fund: Fund) {
        fundViewModel.insertFund(fund)
    }
}