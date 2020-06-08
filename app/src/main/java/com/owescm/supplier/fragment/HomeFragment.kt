package com.owescm.supplier.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.supplier.R
import com.owescm.supplier.adapter.HomeVerticalAdapter
import com.owescm.supplier.model.CountModel
import com.owescm.supplier.model.HomeModel
import com.owescm.supplier.model.ItemDetails
import com.owescm.utils.Constants
import com.owescm.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : androidx.fragment.app.Fragment() {

    var homeList: ArrayList<HomeModel> = ArrayList()
    lateinit var homeVerticalAdapter: HomeVerticalAdapter
    lateinit var homeViewodel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewodel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getAllCounts()
        initRecyclerView()

    }

    private fun getAllCounts() {
        progressBar.visibility = View.VISIBLE
        val map = HashMap<String, String>()
        map["user_id"] = OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1"
        map["api_key"] = OwescmApplication.apiKey
        map["user_type"] = OwescmApplication.userType

        homeViewodel.getAllCounts(map).observe(this, androidx.lifecycle.Observer {
            if (it.status == "success") {
                progressBar.visibility = View.INVISIBLE
                initList(it.data)
            } else {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(context, "Count Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initList(countModel: CountModel.Data) {
        val ebidDetails: ArrayList<ItemDetails> = ArrayList()
        ebidDetails.add(ItemDetails("New", countModel.ebidNew))
        ebidDetails.add(ItemDetails("Saved", countModel.ebidSaved))
        ebidDetails.add(ItemDetails("Live", countModel.ebidLive))
        ebidDetails.add(ItemDetails("Closed", countModel.ebidClosed))

        val eAuction: ArrayList<ItemDetails> = ArrayList()
        eAuction.add(ItemDetails("Today", countModel.eAuctionToday))
        eAuction.add(ItemDetails("Up Coming", countModel.eAuctionUpcoming))
        eAuction.add(ItemDetails("Closed", countModel.eAuctionClosed))

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


        homeVerticalAdapter.notifyDataSetChanged()

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
