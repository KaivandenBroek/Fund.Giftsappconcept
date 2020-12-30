package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.dialogs.NewFundDialog
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

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
    }

}