package com.example.fundgiftsappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.model.Fund
import kotlin.reflect.KFunction1

class FundAdapter(var arrayList: ArrayList<Fund>, private val onClick: KFunction1<Fund, Unit>) :
        RecyclerView.Adapter<FundAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_fund, parent, false)
        )
    }

    /**
     * Returns the amount of movies that are currently shown in the overview
     * @return [Int] Amount of movies
     */
    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fund: Fund, position: Int) {

        }

        // open fund dialog
        init {
            itemView.setOnClickListener {
                onClick(arrayList[adapterPosition])
            }
        }
    }
}