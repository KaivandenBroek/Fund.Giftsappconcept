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
import java.math.RoundingMode
import java.text.DecimalFormat

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

        val current = String.format("%.2f",fundData.currentAmount)
        val stringCurrent = "$current funded so far"

        tvCurrent.text = stringCurrent
        // sets max fundable amount so noone can over-fund
        slider.valueTo = (fundData.fullAmount - fundData.currentAmount).toFloat()

        btnCancel.setOnClickListener { dismiss() }

        btnConfirm.setOnClickListener {

            val title = fundData.fundName
            val amount = slider.value.toDouble()

            if (amount == 0.0) {
                Toast.makeText(context, "No funds added", Toast.LENGTH_LONG).show()
                dismiss()
            } else {

                //round amount
                val rounded = String.format("%.2f", amount)

                // to update the fund i have to replace it
                adapter.arrayList.remove(fundData)
                val updatedFund = Fund(
                        id = fundData.id,
                        fundName = fundData.fundName,
                        creatorId = fundData.creatorId,
                        fullAmount = fundData.fullAmount,
                        currentAmount = fundData.currentAmount + amount
                )

                if (updatedFund.currentAmount >= updatedFund.fullAmount) { // if fund is completed, remove from list

                    updatedFund.id?.let { it1 -> fundViewModel.deleteFund(it1) }
                    adapter.notifyDataSetChanged()

                    Toast.makeText(context, "${fundData.fundName} Completed!", Toast.LENGTH_LONG).show()
                    dismiss()
                } else {
                    updateFund(updatedFund)
                    adapter.arrayList.add(updatedFund)
                    adapter.arrayList.sortByDescending { fund -> (fund.currentAmount / fund.fullAmount) }
                    adapter.notifyDataSetChanged()

                    Toast.makeText(context, "$rounded funded to $title!", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }
        }
    }

    private fun updateFund(fund: Fund) {
        fundViewModel.insertFund(fund)
    }
}