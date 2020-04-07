package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.Fragment.SpendManagementFragment.SpendManagementReportsFragment
import com.owescm.client.Fragment.SpendManagementFragment.SpendManagementStatusFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_spend_management.*

class SpendManagementActivity : AppCompatActivity() {
    val spendManagementFragment = SpendManagementReportsFragment()
    val spendManagementReportsFragment = SpendManagementReportsFragment()
    val spendManagementStatusFragment = SpendManagementStatusFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spend_management)

        openSpendManagementFragment()
        rl_SpendManagement_reports.setOnClickListener {
            openSpendManagementReportsFragment()
        }
        rl_SpendManagement_status.setOnClickListener {
            openSpendManagementStatusFragment()
        }
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

    private fun openSpendManagementFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SpendManagement,spendManagementFragment)
        transaction.commit()
    }
}
