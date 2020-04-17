package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.SalesManagementReportsFragment
import com.owescm.supplier.fragment.SalesManagementStatusFragment
import kotlinx.android.synthetic.main.activity_sales_management.*

class SalesManagementActivity : AppCompatActivity() {
    val salesManagementFragment = SalesManagementReportsFragment()
    val salesManagementReportsFragment = SalesManagementReportsFragment()
    val salesManagementStatusFragment = SalesManagementStatusFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_management)

        setContentView(R.layout.activity_sales_management)
        supportActionBar?.title = "Sales Management"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        openSalesManagementFragment()
        rl_SalesManagement_reports.setOnClickListener {
            openSalesManagementReportsFragment()
        }
        rl_SalesManagement_status.setOnClickListener {
            openSalesManagementStatusFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openSalesManagementReportsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SalesManagement, salesManagementReportsFragment)
        transaction.commit()
    }

    private fun openSalesManagementFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SalesManagement, salesManagementFragment)
        transaction.commit()
    }

    private fun openSalesManagementStatusFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_SalesManagement, salesManagementStatusFragment)
        transaction.commit()
    }
}



