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
import com.example.fundgiftsappconcept.adapters.MyFundAdapter
import com.example.fundgiftsappconcept.dialogs.MyFundDialog
import com.example.fundgiftsappconcept.dialogs.NewFundDialog
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var myFunds = arrayListOf<Fund>()
    private val fundViewModel: FundViewmodel by viewModels() {defaultViewModelProviderFactory}
    private lateinit var myFundAdapter: MyFundAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_new_fund.setOnClickListener {
            val dialog = NewFundDialog()
            dialog.show(parentFragmentManager, "")
        }

        // TODO filter list by fund from current user
        fundViewModel.getAllFunds()

        initViews()
    }

    private fun initViews() {
        myFundAdapter = MyFundAdapter(myFunds,  requireContext(), ::toFund)
        rvMyFunds.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMyFunds.adapter = myFundAdapter
        observeFunds()
    }

    private fun observeFunds() {
        fundViewModel.funds.observe(viewLifecycleOwner, { response ->
            myFunds.clear()
            myFunds.addAll(response)
            myFundAdapter.notifyDataSetChanged()
        })
    }

    private fun toFund(fund: Fund) {
        val dialog = MyFundDialog(fund, myFundAdapter)
        dialog.show(parentFragmentManager,"")
    }

}