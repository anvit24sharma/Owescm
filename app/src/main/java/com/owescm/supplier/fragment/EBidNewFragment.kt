package com.owescm.supplier.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owescm.supplier.R
import com.owescm.supplier.activities.EbidNewDetailsActivity
import com.owescm.supplier.adapter.EbidNewAdapter
import com.owescm.supplier.model.EbidNewModel


class EBidNewFragment : Fragment(), EbidNewAdapter.ClickListener {

    var ebidNewList: ArrayList<EbidNewModel> = ArrayList()
    lateinit var ebidNewListAdapter: EbidNewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ebid_new, container, false)


    }

    override fun onViewDetailsClick() {
        startActivity(Intent(context, EbidNewDetailsActivity::class.java))

    }

}
