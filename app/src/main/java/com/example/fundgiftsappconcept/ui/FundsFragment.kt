package com.example.fundgiftsappconcept.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.dialogs.FundDialog
import kotlinx.android.synthetic.main.fragment_funds.*

class FundsFragment : Fragment(R.layout.fragment_funds) {

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
    }
}