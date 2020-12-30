package com.example.fundgiftsappconcept.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.adapters.FundAdapter
import com.example.fundgiftsappconcept.dialogs.FundDialog
import com.example.fundgiftsappconcept.model.Fund
import kotlinx.android.synthetic.main.fragment_funds.*

class FundsFragment : Fragment(R.layout.fragment_funds) {

    private var funds = arrayListOf<Fund>()
    private lateinit var fundAdapter: FundAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_funds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        circularProgressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(50f, 1000)
            progressBarWidth = 5f
            backgroundProgressBarWidth = 7f
            progressBarColor = Color.GREEN
        }

        btn_fund.setOnClickListener {
            val dialog = FundDialog()
            dialog.show(parentFragmentManager,"")
        }
        initViews()
    }

    private fun initViews() {
        fundAdapter = FundAdapter(funds, ::toFund)
        rvFunds.layoutManager
        rvFunds.adapter = fundAdapter
        observeFunds()
    }

    private fun observeFunds() {
//        viewmodel.list.observe(viewLifecycleOwner, { response ->
//            funds.clear()
//            funds.addAll(response.results)
//            fundAdapter.notifyDataSetChanged()
//        })
//        // Observe the error message.
//        viewModel.errorText.observe(viewLifecycleOwner, {
//            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
//        })
    }

    private fun toFund(fund: Fund) {
        val dialog = FundDialog()
        dialog.show(parentFragmentManager,"")
    }
}