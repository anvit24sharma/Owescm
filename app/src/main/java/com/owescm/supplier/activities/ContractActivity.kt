package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.ContractClosedFragment
import com.owescm.supplier.fragment.ContractOpenFragment
import kotlinx.android.synthetic.main.activity_contract.*

class ContractActivity : AppCompatActivity() {
    val contractFragment = ContractOpenFragment()
    val contractOpenFragment = ContractOpenFragment()
    val contractClosedFragment = ContractClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)

        supportActionBar?.title = "Contract"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        openContractFragment()
        rl_Contract_open.setOnClickListener {
            openContractOpenFragment()
        }
        rl_Contract_closed.setOnClickListener {
            openContractClosedFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openContractClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Contract, contractFragment)
        transaction.commit()
    }

    private fun openContractOpenFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Contract, contractOpenFragment)
        transaction.commit()

    }

    private fun openContractFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Contract, contractClosedFragment)
        transaction.commit()

    }
}

