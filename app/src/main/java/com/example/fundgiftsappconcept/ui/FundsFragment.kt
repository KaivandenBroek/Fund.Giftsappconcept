package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.adapters.FundAdapter
import com.example.fundgiftsappconcept.dialogs.FundDialog
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_funds.*

class FundsFragment : Fragment(R.layout.fragment_funds) {

    private var funds = arrayListOf<Fund>()
    private val fundViewModel: FundViewmodel by viewModels() {defaultViewModelProviderFactory}
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

        fundViewModel.getAllFunds()
        initViews()
    }

    private fun initViews() {
        fundAdapter = FundAdapter(funds,  requireContext(), ::toFund)
        rvFunds.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFunds.adapter = fundAdapter
        observeFunds()
    }

    private fun observeFunds() {
        fundViewModel.funds.observe(viewLifecycleOwner, { response ->
            funds.clear()
            funds.addAll(response)
            fundAdapter.notifyDataSetChanged()
        })
    }

    private fun toFund(fund: Fund) {
        val dialog = FundDialog(fund)
        dialog.show(parentFragmentManager,"")
    }
}