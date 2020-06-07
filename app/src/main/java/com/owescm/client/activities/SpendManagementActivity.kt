package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.fragment.SpendManagementFragment.SpendManagementReportsFragment
import com.owescm.client.fragment.SpendManagementFragment.SpendManagementStatusFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_spend_management.*

class SpendManagementActivity : AppCompatActivity() {
    private val spendManagementReportsFragment = SpendManagementReportsFragment()
    private val spendManagementStatusFragment = SpendManagementStatusFragment()

    var statusCount = 0
    var reportsCount = 0
    var from =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spend_management)

        from = intent.getStringExtra("from")?:""
        statusCount = intent.getIntExtra("ReportsCount",-1)
        reportsCount = intent.getIntExtra("StatusCount",-1)
        initView()
        initClick()
        openFragment()

    }
    private fun openFragment() {
        when (from) {
            "Reports" -> openSpendManagementReportsFragment()
            "Status" -> openSpendManagementStatusFragment()
        }
    }
    private fun initClick() {
        rl_SpendManagement_reports.setOnClickListener {
            openSpendManagementReportsFragment()
        }
        rl_SpendManagement_status.setOnClickListener {
            openSpendManagementStatusFragment()
        }
    }

    private fun initView() {
        tv_count1.text = statusCount.toString()
        tv_count2.text = reportsCount.toString()
    }

    private fun openSpendManagementStatusFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SpendManagement,spendManagementStatusFragment)
        transaction.commit()
    }

    private fun openSpendManagementReportsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SpendManagement,spendManagementReportsFragment)
        transaction.commit()
    }

}
