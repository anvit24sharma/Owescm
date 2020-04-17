package com.owescm.supplier.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.supplier.R
import com.owescm.supplier.adapter.HomeVerticalAdapter
import com.owescm.supplier.model.HomeModel
import com.owescm.supplier.model.ItemDetails
import kotlinx.android.synthetic.main.fragment_home.*


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

        val ebidDetails: ArrayList<ItemDetails> = ArrayList()
        ebidDetails.add(ItemDetails("New", 0))
        ebidDetails.add(ItemDetails("Saved", 0))
        ebidDetails.add(ItemDetails("Live", 0))
        ebidDetails.add(ItemDetails("Closed", 0))

        val eAuction: ArrayList<ItemDetails> = ArrayList()
        eAuction.add(ItemDetails("Today", 0))
        eAuction.add(ItemDetails("Up Coming", 0))
        eAuction.add(ItemDetails("Closed", 0))

        val contract: ArrayList<ItemDetails> = ArrayList()
        contract.add(ItemDetails("Open", 0))
        contract.add(ItemDetails("Closed", 0))

        val supportManagement: ArrayList<ItemDetails> = ArrayList()
        supportManagement.add(ItemDetails("New", 0))
        supportManagement.add(ItemDetails("Open", 0))
        supportManagement.add(ItemDetails("Closed", 0))

        val scoreBoard: ArrayList<ItemDetails> = ArrayList()
        scoreBoard.add(ItemDetails("Current", 0))
        scoreBoard.add(ItemDetails("Updates", 0))


        val salesManagement: ArrayList<ItemDetails> = ArrayList()
        salesManagement.add(ItemDetails("Reports", 0))
        salesManagement.add(ItemDetails("Status", 0))

        homeList.add(HomeModel("eBID", 4, ebidDetails))
        homeList.add(HomeModel("eAuction", 3, eAuction))
        homeList.add(HomeModel("Support Management", 3, supportManagement))
        homeList.add(HomeModel("Score Board", 2, scoreBoard))
        homeList.add(HomeModel("Contract", 2, contract))
        homeList.add(HomeModel("Sales Management", 2, salesManagement))

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

