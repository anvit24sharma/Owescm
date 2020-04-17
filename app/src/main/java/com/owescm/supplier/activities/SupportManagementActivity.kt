package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.SupportManagementClosedFragment
import com.owescm.supplier.fragment.SupportManagementNewFragment
import com.owescm.supplier.fragment.SupportManagementOpenFragment
import kotlinx.android.synthetic.main.activity_support_management.*

class SupportManagementActivity : AppCompatActivity() {
    val supportManagementFragment = SupportManagementNewFragment()
    val supportManagementNewFragment = SupportManagementNewFragment()
    val supportManagementOpenFragment = SupportManagementOpenFragment()
    val supportManagementClosedFragment = SupportManagementClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_support_management)

        supportActionBar?.title = "Support Management"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        opensupportManagementFragment()
        rl_supportManagement_new.setOnClickListener {
            opensupportManagementNewFragment()
        }
        rl_supportManagement_open.setOnClickListener {
            opensupportManagementOpenFragment()
        }
        rl_supportManagement_closed.setOnClickListener {
            opensupportManagementClosedFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun opensupportManagementClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_supportManagement, supportManagementClosedFragment)
        transaction.commit()
    }

    private fun opensupportManagementOpenFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_supportManagement, supportManagementOpenFragment)
        transaction.commit()
    }

    private fun opensupportManagementFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_supportManagement, supportManagementFragment)
        transaction.commit()
    }

    private fun opensupportManagementNewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_supportManagement, supportManagementNewFragment)
        transaction.commit()
    }
}
