package com.example.fundgiftsappconcept.dialogs

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.adapters.MyFundAdapter
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_my_fund.*
import kotlin.math.round

class MyFundDialog(fund: Fund, myFundAdapter: MyFundAdapter) : DialogFragment() {

    private val fundViewModel: FundViewmodel by viewModels()
    private val myAdapter = myFundAdapter
    private val myFund = fund

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_my_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItems()

        btnDismiss.setOnClickListener {
            dismiss()
        }

        btnDelete.setOnClickListener {
            val title = myFund.fundName
            val tmp = myFund // store fund for undo

            myFund.id?.let { id -> fundViewModel.deleteFund(id) }
            Toast.makeText(context, "$title deleted!", Toast.LENGTH_LONG).show()
            myAdapter.arrayList.remove(myFund)
            myAdapter.notifyDataSetChanged()

            // werkt niet
            Snackbar.make(requireView(), R.string.successDelete, Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo) { // undo delete
                        fundViewModel.insertFund(tmp)
                    }
                    .show()

            dismiss()
        }
    }

    private fun setItems() {

        fund_title.text = myFund.fundName
        tfFundsGathered.text = String.format("Amount funded: %.2f", myFund.currentAmount.round(2))
        circularProgressBar.apply {
            val cash = myFund.currentAmount.toFloat()
            val max = myFund.fullAmount.toFloat()

            // Set Progress
            progress = cash
            setProgressWithAnimation(cash, 1000) // =1s
            progressMax = max

            // Set Color
            progressBarColor = Color.GREEN
            backgroundProgressBarColor = Color.LTGRAY

            // Set Width
            progressBarWidth = 16f // in DP
            backgroundProgressBarWidth = 8f // in DP
        }
    }

    // more than 2 decimals seems a bit excessive, so i round it
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}