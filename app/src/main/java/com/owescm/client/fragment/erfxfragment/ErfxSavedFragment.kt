package com.owescm.client.fragment.erfxfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.OwescmApplication
import com.owescm.client.R
import com.owescm.client.adapter.ErfxSavedAdapter
import com.owescm.client.model.ErfxSavedListResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import kotlinx.android.synthetic.main.fragment_erfx_saved.*
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set


class ErfxSavedFragment : Fragment() {
    lateinit var homeViewModel: HomeViewModel
    var erfxSavedList: ArrayList<ErfxSavedListResponse.Data> = ArrayList()
    lateinit var erfxSavedListAdapter: ErfxSavedAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_erfx_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getErfxSavedList()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        erfxSavedListAdapter = erfxSavedList.let {
            ErfxSavedAdapter(context, it)
        }

        rv_erfxSavedList.apply {
            adapter = erfxSavedListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun getErfxSavedList() {
        progress_Bar.visibility = View.VISIBLE
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = Constants.toRequestBody(OwescmApplication.apiKey)
        map["user_type"] = Constants.toRequestBody(OwescmApplication.userType)
        map["user_id"] = Constants.toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1")
                ?: "-1")

        homeViewModel.getErfxSavedList(map).observe(viewLifecycleOwner, Observer {
            if (it.status == "success") {
                progress_Bar.visibility = View.INVISIBLE
                erfxSavedList.addAll(it.data)
                erfxSavedListAdapter.notifyDataSetChanged()
            } else {
                progress_Bar.visibility = View.INVISIBLE
                Toast.makeText(context, "Erfx Saved List Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
