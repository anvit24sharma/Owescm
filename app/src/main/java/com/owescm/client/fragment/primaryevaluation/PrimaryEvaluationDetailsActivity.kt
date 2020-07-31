package com.owescm.client.fragment.primaryevaluation

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
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
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.MainActivity
import com.owescm.client.R
import com.owescm.client.adapter.PrimaryEvaluationSuppliersAdapter
import com.owescm.client.model.PrimaryEvaluationDetailsResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.remote.DownloadService
import com.owescm.services.local.Download
import com.owescm.utils.Constants
import com.owescm.utils.Constants.Companion.toRequestBody
import kotlinx.android.synthetic.main.activity_primary_details.*
import kotlinx.android.synthetic.main.primary_evaluation_details_shortlist_dialog.view.*
import okhttp3.RequestBody
import java.util.*


class PrimaryEvaluationDetailsActivity : AppCompatActivity(),
    PrimaryEvaluationSuppliersAdapter.ClickListener {
    companion object {
        val MESSAGE_PROGRESS = "message_progress"
        val PERMISSION_REQUEST_CODE = 1

    }
    var erfxId = ""
    var suppliers = ""
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

    @SuppressLint("SetTextI18n")
    private fun initClick() {
        btnDownload.setOnClickListener {
            if (checkPermission()) {
                startDownload()
            } else {
                requestPermission()
            }
        }

        btnCreate.setOnClickListener {

            if (suppliers != "") {
                suppliers = suppliers.substring(0, suppliers.length - 1);
                val mDialogView = LayoutInflater.from(this)
                    .inflate(R.layout.primary_evaluation_details_shortlist_dialog, null)
                val mBuilder = android.app.AlertDialog.Builder(this).setView(mDialogView)
                val mAlertDialog = mBuilder.show()

                val auctionDate: LinearLayout = mDialogView.findViewById(R.id.ll_auctionDate)
                val auctionTime: LinearLayout = mDialogView.findViewById(R.id.ll_auctionTime)
                val reductionTime: EditText = mDialogView.findViewById(R.id.et_reductionPercent)
                val reductionPercent: EditText = mDialogView.findViewById(R.id.et_reductionTimer)
                val auctionDateText: TextView = mDialogView.findViewById(R.id.tvAuctionDate)
                val auctionTimeText: TextView = mDialogView.findViewById(R.id.tvValidTill)
                val c = Calendar.getInstance()
                val mYear = c[Calendar.YEAR]
                val mMonth = c[Calendar.MONTH]
                val mDay = c[Calendar.DAY_OF_MONTH]
                val mHour = c.get(Calendar.HOUR_OF_DAY)
                val mMinute = c.get(Calendar.MINUTE)

                auctionDate.setOnClickListener {
                    val datePickerDialog = DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            auctionDateText.text = year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()
                        }, mYear, mMonth, mDay
                    )
                    datePickerDialog.show()
                }

                auctionTime.setOnClickListener {
                    val timePickerDialog = TimePickerDialog(this,
                        TimePickerDialog.OnTimeSetListener() { view, hour, minute ->
                            auctionTimeText.text = "$hour:$minute"
                        }, mHour, mMinute, true
                    )
                    timePickerDialog.show()
                }

                mDialogView.btn_create.setOnClickListener {
                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("Creating...")
                    progressDialog.setCanceledOnTouchOutside(false)

                    val map: MutableMap<String, RequestBody?> = HashMap()
                    if (reductionPercent.text.toString() == "" || reductionTime.text.toString() == "" ||
                        auctionDateText.text.toString() == "" || auctionTimeText.text.toString() == ""
                    ) {
                        Toast.makeText(this, "Please Fill All Details.", Toast.LENGTH_SHORT).show()
                    } else {
                        progressDialog.show()

                        map["api_key"] = toRequestBody(apiKey)
                        map["user_type"] = toRequestBody(userType)
                        map["erfx_id"] = toRequestBody(erfxId)
                        map["auctionDate"] = toRequestBody(auctionDateText.text.toString())
                        map["auctionTime"] = toRequestBody(auctionTimeText.text.toString())
                        map["reduction"] = toRequestBody(reductionPercent.text.toString())
                        map["reduction_time"] = toRequestBody(reductionTime.text.toString())
                        map["user_id"] = toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1")
                        map["shortlisted_suppliers_data"] = toRequestBody(suppliers)
                        homeViewModel.createEAuction(map).observe(this, androidx.lifecycle.Observer {
                            if (it.status == "success") {
                                progressDialog.dismiss()
                                mAlertDialog.dismiss()
                                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                                finish()
                                val intent = Intent(this,MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                startActivity(intent)
                            } else {
                                progressDialog.dismiss()
                            }
                        })
                    }
                }

                mDialogView.btn_close.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            else{
                Toast.makeText(this,"Shortlist atleast 1 supplier",Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun startDownload() {
        val intent = Intent(this, DownloadService::class.java)
        intent.putExtra("erfxDocName", primaryEvaluationDetails.data.primaryEvaluationOpen[0].erfxDoc)
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
                    Toast.makeText(context,"File Download Complete",Toast.LENGTH_SHORT).show()
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
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
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

    override fun onShortlistClick(supplierId: String) {
        suppliers += "$supplierId,"
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
        map["api_key"] = toRequestBody(apiKey)
        map["user_type"] = toRequestBody(userType)
        map["user_id"] = toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1")
        map["erfx_id"] = toRequestBody(erfxId)

        homeViewModel.getPrimaryEvaluationDetails(map).observe(this, Observer {
            if (it.status == "success") {
                progressBar.visibility = View.INVISIBLE

                primaryEvaluationDetails = it
                initView(primaryEvaluationDetails)
                initRecyclerView()
            } else {
                Toast.makeText(this, "Primary Evaluation Details Api Failed", Toast.LENGTH_SHORT)
                    .show()
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


}



