package com.example.fundgiftsappconcept.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.model.Fund
import kotlinx.android.synthetic.main.card_fund.view.*
import kotlin.reflect.KFunction1

class MyFundAdapter(var arrayList: ArrayList<Fund>, val context: Context, private val onClick: KFunction1<Fund, Unit>) :
        RecyclerView.Adapter<MyFundAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fund: Fund) {
            itemView.tvFundTitle.text = fund.fundName
            itemView.circularProgressBar.apply {
                val cash = fund.currentAmount.toFloat()
                val max = fund.fullAmount.toFloat()

                // Set Progress
                progress = cash
                setProgressWithAnimation(cash, 1000) // =1s
                progressMax = max

                // Set Color
                progressBarColor = Color.GREEN
                backgroundProgressBarColor = Color.BLACK

                // Set Width
                progressBarWidth = 16f // in DP
                backgroundProgressBarWidth = 8f // in DP
            }
        }
        // open fund dialog
        init {
            itemView.setOnClickListener {
                onClick(arrayList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_fund, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }
}