package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.fragment.primaryevaluation.PrimaryEvaluationClosedFragment
import com.owescm.client.fragment.primaryevaluation.PrimaryEvaluationOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_primary_evaluation.*

class PrimaryEvaluationActivity : AppCompatActivity() {
    private val primaryEvaluationOpenFragment = PrimaryEvaluationOpenFragment()
    private val primaryEvaluationClosedFragment = PrimaryEvaluationClosedFragment()
    var from =""
    var openCount = 0
    var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_evaluation)

        from = intent.getStringExtra("from")?:""
        openCount = intent.getIntExtra("OpenCount",-1)
        closedCount = intent.getIntExtra("ClosedCount",-1)
        initView()
        openFragment()

        rl_PrimaryEvaluation_open.setOnClickListener {
            openPrimaryEvaluationOpenFragment()
        }
        rl_PrimaryEvaluation_closed.setOnClickListener {
            openPrimaryEvaluationClosedFragment()
        }
    }


    private fun openFragment() {
        when (from) {
            "Open" -> openPrimaryEvaluationOpenFragment()
            "Closed" -> openPrimaryEvaluationClosedFragment()
        }
    }

    private fun initView() {
        tv_count1.text = openCount.toString()
        tv_count2.text = closedCount.toString()
    }

    private fun openPrimaryEvaluationClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_PrimaryEvaluation,primaryEvaluationClosedFragment)
        transaction.commit()
    }

    private fun openPrimaryEvaluationOpenFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_PrimaryEvaluation, primaryEvaluationOpenFragment)
        transaction.commit()
    }


}
