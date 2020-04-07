package com.owescm.client.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.Fragment.ContractFragment.ContractClosedFragment
import com.owescm.client.Fragment.ContractFragment.ContractOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_contract.*

class ContractActivity : AppCompatActivity() {
    val contractFragment=ContractOpenFragment()
    val contractOpenFragment = ContractOpenFragment()
    val contractClosedFragment = ContractClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        openContractFragment()
        rl_Contract_open.setOnClickListener{
            openContractOpenFragment()
        }
        rl_Contract_closed.setOnClickListener{
            openContractClosedFragment()
        }
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
