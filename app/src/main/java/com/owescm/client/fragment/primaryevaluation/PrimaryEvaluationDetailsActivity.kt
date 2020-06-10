package com.owescm.client.fragment.primaryevaluation

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.client.adapter.PrimaryEvaluationSuppliersAdapter
import com.owescm.client.R
import com.owescm.client.model.PrimaryEvaluationDetailsResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.remote.DownloadService
import com.owescm.services.local.Download
import com.owescm.utils.Constants
import kotlinx.android.synthetic.main.activity_primary_details.*
import okhttp3.RequestBody
import java.util.HashMap


class PrimaryEvaluationDetailsActivity : AppCompatActivity(),
    PrimaryEvaluationSuppliersAdapter.ClickListener {
    companion object {
        val MESSAGE_PROGRESS = "message_progress"
        val PERMISSION_REQUEST_CODE = 1

    }
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
        initClick()
        registerReceiver()
    }

    private fun initClick() {
        btnDownload.setOnClickListener {
            if (checkPermission()) {
                startDownload()
            } else {
                requestPermission()
            }
        }
    }

    private fun startDownload() {
        val intent = Intent(this, DownloadService::class.java)
        startService(intent)
    }

    private fun registerReceiver() {
        val bManager = LocalBroadcastManager.getInstance(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);
    }


    private val broadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action.equals(MESSAGE_PROGRESS)) {
                val download = intent?.getParcelableExtra<Download>("download")
                // mProgressBar.setProgress(download.getProgress());
                if (download?.progress == 100) {

                    //  mProgressText.setText("File Download Complete");

                } else {

                    //  mProgressText.setText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));

                }
            }
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownload()
                } else {
                    //Snackbar.make(findViewById(R.id.coordinatorLayout),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();
                }
        }
    }

    override fun onShortlistClick() {

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
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = Constants.toRequestBody(OwescmApplication.apiKey)
        map["user_type"] = Constants.toRequestBody(OwescmApplication.userType)
        map["user_id"] = Constants.toRequestBody(
            OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1"
        )
        map["erfx_id"] = Constants.toRequestBody(erfxId)

        homeViewModel.getPrimaryEvaluationDetails(map).observe(this, Observer {
            if (it.status == "success") {
                primaryEvaluationDetails = it
                initView(primaryEvaluationDetails)
                initRecyclerView()
            } else {
                Toast.makeText(this, "Primary Evaluation Details Api Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun initView(primaryEvaluationDetails: PrimaryEvaluationDetailsResponse) {
        tv_eRFX_No.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].generatedErfxId
        tv_clientName.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].name
        tv_location.text = primaryEvaluationDetails.data.primaryEvaluationOpen[0].location

    }

}



