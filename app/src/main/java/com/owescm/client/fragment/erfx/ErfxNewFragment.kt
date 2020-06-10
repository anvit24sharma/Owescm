package com.owescm.client.fragment.erfx

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.adapter.InvitedSuppliersListAdapter
import com.owescm.client.R
import com.owescm.client.model.ErfxModel
import com.owescm.client.model.InvitedSuppliersListModel
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import com.owescm.utils.FileUtils
import kotlinx.android.synthetic.main.fragment_erfx_new.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


class ErfxNewFragment : Fragment(), ErfxNewInviteSuppliersBSFragment.BottomSheetListner {

    var invitedSuppliersList = ArrayList<InvitedSuppliersListModel>()
    private val READ_STORAGE_PERMISSION_REQUEST_CODE = 101
    lateinit var homeViewModel: HomeViewModel
    var category = arrayOf("Security", "B", "C", "D")
    var subCategory0 = arrayOf("a", "b", "c")
    var subCategory1 = arrayOf("d", "e", "f")
    var subCategory2 = arrayOf("g", "h", "i")
    var subCategory3 = arrayOf("j", "k", "l")
    var selectedCategory: String = ""
    var selectedSubCategory: String = ""
    var string: String = ""
    lateinit var invitedSuppliersListAdapter: InvitedSuppliersListAdapter
    lateinit var subcategory: Array<String>
    private val FILE_SELECT_CODE = 0
    var path: String? = null
    var inviteBSFragment : ErfxNewInviteSuppliersBSFragment ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_erfx_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        onClick()
        initSpinners()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        invitedSuppliersListAdapter = invitedSuppliersList.let {
            InvitedSuppliersListAdapter(context, it)
        }

        rvInvitedSuppliers.apply {
            adapter = invitedSuppliersListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun initSpinners() {
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                subcategory = when (position) {
                    0 -> {
                        subCategory0
                    }
                    1 -> {
                        subCategory1
                    }
                    2 -> {
                        subCategory2
                    }
                    else -> {
                        subCategory3
                    }
                }
                val subCategoryAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, subcategory)
                subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerSubCategory.adapter = subCategoryAdapter
                selectedCategory = category[position]
            }
        }

        val categoryAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, category)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter

        spinnerSubCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSubCategory = subcategory[position].toString()
            }

        }


    }

    private fun onClick() {
        btn_create.setOnClickListener {
            val map: MutableMap<String, RequestBody?> = HashMap()
            if (selectedCategory == "" || selectedSubCategory == "") {
                Toast.makeText(
                    context,
                    "Please select Category and SubCategory.",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (et_from.text.toString() == "" ||
                et_to.text.toString() == "" ||
                et_headLine.text.toString() == "" ||
                et_loc.text.toString() == "" ||
                et_paymentTerms.text.toString() == "" ||
                et_specialReq.text.toString() == ""
            ) {
                Toast.makeText(context, "Please Fill All Details.", Toast.LENGTH_SHORT).show()

            } else {
                val erfxModel = ErfxModel(apiKey, userType, "1", et_from.text.toString(), et_to.text.toString(), et_headLine.text.toString(), et_loc.text.toString(), et_paymentTerms.text.toString(), et_specialReq.text.toString(), "1", OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1", invitedSuppliersList)

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

                if (!checkPermissionForReadExtertalStorage()) {
                    requestPermissionForReadExtertalStorage()
                }
                if (path != null) {
                    val file = File(path)
                    val requestFile: RequestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val body = MultipartBody.Part.createFormData("erfxDoc", file.name, requestFile)
                    homeViewModel.createErfx(map, body)
                } else {
                    Toast.makeText(context, "Please Select File.", Toast.LENGTH_SHORT).show()

                }
            }

        }


        et_from.setOnClickListener {

            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                context!!,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    et_from.text =
                        year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        et_to.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                context!!,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    et_to.text =
                        year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        btn_choose.setOnClickListener {
            showFileChooser()
        }

        btn_invite.setOnClickListener {
            inviteBSFragment = ErfxNewInviteSuppliersBSFragment("string", this)
            inviteBSFragment?.show(fragmentManager!!, "schedule")
        }
    }


    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE)
        } catch (ex: ActivityNotFoundException) { // Potentially direct the user to the Market with a Dialog
            Toast.makeText(context, "Please install a File Manager.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toRequestBody(value: String?): RequestBody? {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value)
    }


    private fun checkPermissionForReadExtertalStorage(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = context!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            FILE_SELECT_CODE ->
                if (resultCode == RESULT_OK) {
                    val uri = data?.data
                    path = FileUtils.getPath(context!!, uri!!)
                    if (path != null) {
                        val filename = path?.substring(path?.lastIndexOf("/")?.plus(1)!!)
                        et_chooseFile.text = filename
                    }

                }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onSaveClicked(invitedSuppliersListModel: InvitedSuppliersListModel) {
        invitedSuppliersList.add(invitedSuppliersListModel)
        inviteBSFragment?.dismiss()
        invitedSuppliersListAdapter.notifyDataSetChanged()
    }
}
