package com.example.fundgiftsappconcept.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundgiftsappconcept.R
import com.example.fundgiftsappconcept.adapters.FriendsAdapter
import com.example.fundgiftsappconcept.adapters.FundAdapter
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.model.User
import com.example.fundgiftsappconcept.viewModels.FundViewmodel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_funds.*

class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private var friends = arrayListOf<User>()
    private val fundViewModel: FundViewmodel by viewModels() {defaultViewModelProviderFactory}
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fundViewModel.getAllFriends()
        initViews()
    }

    private fun initViews() {
        friendsAdapter = FriendsAdapter(friends,  requireContext())
        rvFriends.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFriends.adapter = friendsAdapter
        observeFunds()
    }

    private fun observeFunds() {
        fundViewModel.friends.observe(viewLifecycleOwner, { response ->
            friends.clear()
            friends.addAll(response)
            friendsAdapter.notifyDataSetChanged()
        })
    }
}