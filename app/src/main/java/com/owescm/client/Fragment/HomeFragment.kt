package com.owescm.client.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.OwescmApplication.Companion.prefs
import com.owescm.client.R
import com.owescm.client.adapter.HomeVerticalAdapter
import com.owescm.client.model.CountModel
import com.owescm.client.model.HomeModel
import com.owescm.client.model.ItemDetails
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants.Companion.USER_ID
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
        val map = HashMap<String, String>()
        map["user_id"] = prefs.getString(USER_ID, "-1") ?: "-1"
        map["api_key"] = OwescmApplication.apiKey
        map["user_type"] = OwescmApplication.userType

        homeViewodel.getAllCounts(map).observe(this, androidx.lifecycle.Observer {
            if (it.status == "success") {
                initList(it.data)
            } else {
                Toast.makeText(context, "Count Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initList(countModel: CountModel.Data) {
        val erfxDetails: ArrayList<ItemDetails> = ArrayList()
        erfxDetails.add(ItemDetails("New", -1))
        erfxDetails.add(ItemDetails("Saved", countModel.erfxSaved))
        erfxDetails.add(ItemDetails("Live", countModel.erfxLive))
        erfxDetails.add(ItemDetails("Closed", countModel.erfxClosed))

        val eAuction: ArrayList<ItemDetails> = ArrayList()
        eAuction.add(ItemDetails("Today", countModel.eAuctionToday))
        eAuction.add(ItemDetails("Up Coming", countModel.eAuctionUpcoming))
        eAuction.add(ItemDetails("Closed", countModel.eAuctionClosed))

        val primaryEvaluation: ArrayList<ItemDetails> = ArrayList()
        primaryEvaluation.add(ItemDetails("Open", countModel.primaryEvaluationOpen))
        primaryEvaluation.add(ItemDetails("Closed", countModel.primaryEvaluationClosed))

        val finalEvaluation: ArrayList<ItemDetails> = ArrayList()
        finalEvaluation.add(ItemDetails("Open", countModel.finalEvaluationOpen))
        finalEvaluation.add(ItemDetails("Closed", countModel.finalEvaluationClosed))

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
