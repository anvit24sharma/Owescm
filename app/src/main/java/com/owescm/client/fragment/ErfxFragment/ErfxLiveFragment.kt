package com.owescm.client.fragment.ErfxFragment

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
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.Adapter.ErfxLiveAdapter
import com.owescm.client.R
import com.owescm.client.model.ErfxLiveListResponse
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import com.owescm.utils.Constants.Companion.toRequestBody
import kotlinx.android.synthetic.main.fragment_erfx_live.*
import okhttp3.RequestBody
import java.util.HashMap


class ErfxLiveFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    var erfxLiveList: ArrayList<ErfxLiveListResponse.Data> = ArrayList()
    lateinit var erfxLiveListAdapter: ErfxLiveAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_erfx_live, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getErfxLiveList()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        erfxLiveListAdapter = erfxLiveList.let {
            ErfxLiveAdapter(context, it)
        }

        rv_erfxLiveList.apply {
            adapter = erfxLiveListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun getErfxLiveList() {
        progressBar.visibility = View.VISIBLE
        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = toRequestBody(apiKey)
        map["user_type"] = toRequestBody(userType)
        map["user_id"] = toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1")
                ?: "-1")

        homeViewModel.getErfxLiveList(map).observe(this, Observer {
            if (it.status == "success") {
                progressBar.visibility = View.INVISIBLE
                erfxLiveList.addAll(it.data)
                erfxLiveListAdapter.notifyDataSetChanged()
            } else {
                progressBar.visibility = View.INVISIBLE

                Toast.makeText(context, "Erfx Saved List Api Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
