package com.owescm.client.fragment.primaryevaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import kotlinx.android.synthetic.main.primary_evaluation_details_shortlist_dialog.*
import kotlinx.android.synthetic.main.primary_evaluation_details_shortlist_dialog.view.*
import okhttp3.RequestBody
import java.util.HashMap


class PrimaryEvaluationDetailsActivity : AppCompatActivity(), PrimaryEvaluationSuppliersAdapter.ClickListener {

    var erfxId = ""
    lateinit var homeViewModel: HomeViewModel
    lateinit var primaryEvaluationDetails: PrimaryEvaluationDetailsResponse
    lateinit var peDetailsAdapter: PrimaryEvaluationSuppliersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_details)

        erfxId = intent?.getStringExtra("erfxId") ?: ""
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getPrimaryEvaluationDetails()
        btnClick()
    }

    private fun btnClick() {
        btnCreate.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.primary_evaluation_details_shortlist_dialog, null)
            val mBuilder = android.app.AlertDialog.Builder(this).setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            mDialogView.btn_create.setOnClickListener {
                mAlertDialog.dismiss()


            }
            mDialogView.btn_close.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }


    private fun initRecyclerView() {
        peDetailsAdapter = primaryEvaluationDetails.data.erfxReplies.let {
            PrimaryEvaluationSuppliersAdapter(this, it, this)
        }

        rvSuppliers.apply {
            adapter = peDetailsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

    }

    private fun getPrimaryEvaluationDetails() {
        progressBar.visibility = View.VISIBLE
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = Constants.toRequestBody(OwescmApplication.apiKey)
        map["user_type"] = Constants.toRequestBody(OwescmApplication.userType)
        map["user_id"] = Constants.toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1")
                ?: "-1")
        map["erfx_id"] = Constants.toRequestBody(erfxId)

        homeViewModel.getPrimaryEvaluationDetails(map).observe(this, Observer {
            if (it.status == "success") {
                progressBar.visibility = View.INVISIBLE

                primaryEvaluationDetails = it
                initView(primaryEvaluationDetails)
                initRecyclerView()
            } else {
                progressBar.visibility = View.INVISIBLE

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
