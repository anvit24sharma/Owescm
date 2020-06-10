package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.fragment.ContractFragment.ContractOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_contract.*

class ContractActivity : AppCompatActivity() {
    val contractFragment=ContractOpenFragment()
    val contractOpenFragment = ContractOpenFragment()
    var from =""
    var openCount = 0
    var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        openFragment()
        from = intent.getStringExtra("from")?:""
        openCount = intent.getIntExtra("OpenCount",-1)
        closedCount = intent.getIntExtra("ClosedCount",-1)
        initView()
        initClick()

    }

    private fun openFragment() {
        when (from) {
            "Open" -> openContractClosedFragment()
            "Closed" -> openContractOpenFragment()
        }
    }

    private fun initClick() {
        rl_Contract_open.setOnClickListener{
            openContractOpenFragment()
        }

        rl_Contract_closed.setOnClickListener{
            openContractClosedFragment()
        }
    }

    private fun initView() {
        tv_count1.text = openCount.toString()
        tv_count2.text = closedCount.toString()
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


}
