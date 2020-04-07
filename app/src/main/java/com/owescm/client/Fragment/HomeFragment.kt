package com.owescm.client.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.adapter.HomeVerticalAdapter
import com.owescm.client.Model.HomeModel
import com.owescm.client.Model.ItemDetails
import com.owescm.client.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : androidx.fragment.app.Fragment() {

    var homeList: ArrayList<HomeModel> = ArrayList()

    lateinit var homeVerticalAdapter: HomeVerticalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val erfxDetails: ArrayList<ItemDetails> = ArrayList()
        erfxDetails.add(ItemDetails("New", -1))
        erfxDetails.add(ItemDetails("Saved", 0))
        erfxDetails.add(ItemDetails("Live", 0))
        erfxDetails.add(ItemDetails("Closed", 0))

        val eAuction: ArrayList<ItemDetails> = ArrayList()
        eAuction.add(ItemDetails("Today", 0))
        eAuction.add(ItemDetails("Up Coming", 0))
        eAuction.add(ItemDetails("Closed", 0))

        val primaryEvaluation: ArrayList<ItemDetails> = ArrayList()
        primaryEvaluation.add(ItemDetails("Open", 0))
        primaryEvaluation.add(ItemDetails("Closed", 0))

        val finalEvaluation: ArrayList<ItemDetails> = ArrayList()
        finalEvaluation.add(ItemDetails("Open", 0))
        finalEvaluation.add(ItemDetails("Closed", 0))

        val contract: ArrayList<ItemDetails> = ArrayList()
        contract.add(ItemDetails("Open", 0))
        contract.add(ItemDetails("Closed", 0))

        val spendManagement: ArrayList<ItemDetails> = ArrayList()
        spendManagement.add(ItemDetails("Reports", 0))
        spendManagement.add(ItemDetails("Status", 0))

        homeList.add(HomeModel("eRfx", 4, erfxDetails))
        homeList.add(HomeModel("eAuction", 3, eAuction))
        homeList.add(HomeModel("Primary Evaluation", 2, primaryEvaluation))
        homeList.add(HomeModel("Final Evaluation", 2, finalEvaluation))
        homeList.add(HomeModel("Contract", 2, contract))
        homeList.add(HomeModel("Spend Management", 2, spendManagement))

        initRecyclerView()

    }

    private fun initRecyclerView() {
        homeVerticalAdapter = homeList.let {
            HomeVerticalAdapter(context, it)
        }

        rv_menu.apply {
            adapter = homeVerticalAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


}
