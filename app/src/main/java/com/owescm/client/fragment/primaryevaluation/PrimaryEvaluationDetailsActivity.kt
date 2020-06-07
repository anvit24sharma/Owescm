package com.owescm.client.fragment.primaryevaluation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.client.adapter.PrimaryEvaluationSuppliersAdapter
import com.owescm.client.R
import com.owescm.client.model.PrimaryEvaluationDetailsResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import kotlinx.android.synthetic.main.activity_primary_details.*
import okhttp3.RequestBody
import java.util.HashMap


class PrimaryEvaluationDetailsActivity : AppCompatActivity(), PrimaryEvaluationSuppliersAdapter.ClickListener {

    var erfxId = ""
    lateinit var homeViewModel: HomeViewModel
    lateinit  var primaryEvaluationDetails: PrimaryEvaluationDetailsResponse
    lateinit var peDetailsAdapter: PrimaryEvaluationSuppliersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_details)

        erfxId = intent?.getStringExtra("erfxId")?:""
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getPrimaryEvaluationDetails()
    }


    private fun initRecyclerView() {
        peDetailsAdapter = primaryEvaluationDetails.data.erfxReplies.let {
            PrimaryEvaluationSuppliersAdapter(this, it,this)
        }

        rvSuppliers.apply {
                adapter = peDetailsAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

    }
    private fun getPrimaryEvaluationDetails() {
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = Constants.toRequestBody(OwescmApplication.apiKey)
        map["user_type"] = Constants.toRequestBody(OwescmApplication.userType)
        map["user_id"] = Constants.toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1")
        map["erfx_id"] = Constants.toRequestBody(erfxId)

        homeViewModel.getPrimaryEvaluationDetails(map).observe(this, Observer {
            if (it.status == "success") {
                primaryEvaluationDetails = it
                initView(primaryEvaluationDetails)
                initRecyclerView()
            } else {
                Toast.makeText(this, "Primary Evaluation Details Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView(primaryEvaluationDetails: PrimaryEvaluationDetailsResponse) {
        tv_eRFX_No.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].generatedErfxId
        tv_clientName.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].name
        tv_location.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].location

    }

    override fun onShortlistClick() {
    }


}
