package com.owescm.client.fragment.primaryevaluation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.client.adapter.PrimaryEvaluationOpenAdapter
import com.owescm.client.R
import com.owescm.client.model.OpenPrimaryEvaluationListResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import kotlinx.android.synthetic.main.fragment_primary_evaluation_open.*
import okhttp3.RequestBody
import java.util.HashMap


class PrimaryEvaluationOpenFragment : Fragment(), PrimaryEvaluationOpenAdapter.ClickListener {

    lateinit var homeViewModel: HomeViewModel
    var peOpenList: ArrayList<OpenPrimaryEvaluationListResponse.Data> = ArrayList()
    lateinit var peOpenListAdapter: PrimaryEvaluationOpenAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_primary_evaluation_open, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getOpenPrimaryEvaluationList()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        peOpenListAdapter = peOpenList.let {
            PrimaryEvaluationOpenAdapter(context, it,this)
        }

        rv_peOpenList.apply {
            adapter = peOpenListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun getOpenPrimaryEvaluationList() {
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = Constants.toRequestBody(OwescmApplication.apiKey)
        map["user_type"] = Constants.toRequestBody(OwescmApplication.userType)
        map["user_id"] = Constants.toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1")
                ?: "-1")

        homeViewModel.getOpenPrimaryEvaluationList(map).observe(this, Observer {
            if (it.status == "success") {
                peOpenList.addAll(it.data)
                peOpenListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Primary Evaluation Open List Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSelectClick(erfxId: String) {
        val intent = Intent(context,PrimaryEvaluationDetailsActivity::class.java)
        intent.putExtra("erfxId",erfxId)
        startActivity(intent)
    }

}
