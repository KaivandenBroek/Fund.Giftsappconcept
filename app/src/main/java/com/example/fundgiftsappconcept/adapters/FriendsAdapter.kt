package com.example.fundgiftsappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.model.User
import kotlinx.android.synthetic.main.card_friend.view.*
import kotlinx.android.synthetic.main.card_fund.view.*

class FriendsAdapter(var arrayList: ArrayList<User>, val context: Context) :
RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.tvFriendName.text = user.name
            itemView.tvFriendEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_friend, parent, false)
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