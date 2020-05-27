package com.owescm.client.Fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.OwescmApplication.Companion.prefs
import com.owescm.client.R
import com.owescm.client.adapter.HomeVerticalAdapter
import com.owescm.client.model.CountModel
import com.owescm.client.model.ErfxModel
import com.owescm.client.model.HomeModel
import com.owescm.client.model.ItemDetails
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants.Companion.USER_ID
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


class HomeFragment : androidx.fragment.app.Fragment() {

    var homeList: ArrayList<HomeModel> = ArrayList()
    lateinit var homeVerticalAdapter: HomeVerticalAdapter
    lateinit var homeViewodel: HomeViewModel
    val READ_STORAGE_PERMISSION_REQUEST_CODE = 101
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

        val map: MutableMap<String, RequestBody?> = HashMap()
        val erfxModel = ErfxModel(
            OwescmApplication.apiKey,
            OwescmApplication.userType,
            "Security",
            "2020-07-24",
            "2021-07-29",
            "Anything",
            "Delhi",
            "ds",
            "ckn",
            "ndjvndjk",
            prefs.getString(USER_ID, "-1") ?: "-1"
        )
        map["api_key"] = toRequestBody(erfxModel.apiKey)
        map["user_type"] = toRequestBody(erfxModel.userType)
        map["category"] = toRequestBody(erfxModel.category)
        map["contractPeriodFrom"] = toRequestBody(erfxModel.contractPeriodFrom)
        map["contractPeriodTo"] = toRequestBody(erfxModel.contractPeriodTo)
        map["headline"] = toRequestBody(erfxModel.headline)
        map["location"] = toRequestBody(erfxModel.location)
        map["payment_terms"] = toRequestBody(erfxModel.paymentTerms)
        map["specialRequirement"] = toRequestBody(erfxModel.specialRequirement)
        map["subCategory"] = toRequestBody(erfxModel.subCategory)
        map["user_id"] = toRequestBody(erfxModel.userId)

        if(!checkPermissionForReadExtertalStorage()){
            requestPermissionForReadExtertalStorage()
        }
        val file = File("/storage/emulated/0/Download/Covid19TamilnaduAdvisory.pdf")
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("erfxDoc", file.getName(), requestFile)
       // map["erfxDoc"] = requestFile
        homeViewodel.createErfx(map,body)

        val map1 = HashMap<String, String>()
        map1["user_id"] = prefs.getString(USER_ID, "-1") ?: "-1"
        map1["api_key"] = OwescmApplication.apiKey
        map1["user_type"] = OwescmApplication.userType

//        homeViewodel.getAllCounts(map1).observe(this, androidx.lifecycle.Observer {
//            if (it.status == "success") {
//                initList(it.data)
//            } else {
//                Toast.makeText(context, "Count Api Failed", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    fun toRequestBody(value: String?): RequestBody? {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value)
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

    fun checkPermissionForReadExtertalStorage(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = context!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
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
    @Throws(Exception::class)
    fun requestPermissionForReadExtertalStorage() {
        try {

            ActivityCompat.requestPermissions(
                (context as Activity?)!!,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_PERMISSION_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}
