package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.fragment.FinalEvaluationFragment.FinalEvaluationClosedFragment
import com.owescm.client.fragment.FinalEvaluationFragment.FinalEvaluationOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_contract.tv_count1
import kotlinx.android.synthetic.main.activity_final_evaluation.*

class FinalEvaluationActivity : AppCompatActivity() {
    val finalEvaluationOpenFragment = FinalEvaluationOpenFragment()
    val finalEvaluationClosedFragment = FinalEvaluationClosedFragment()
    var from =""
    var openCount = 0
    var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_evaluation)
        from = intent.getStringExtra("from")?:""

        openFragment()
        openCount = intent.getIntExtra("OpenCount",-1)
        closedCount = intent.getIntExtra("ClosedCount",-1)
        initView()
        initClicks()

    }

    private fun openFragment() {
        when (from) {
            "Open" -> openFinalEvaluationOpenFragment()
            "Closed" -> openFinalEvaluationClosedFragment()
        }
    }

        private fun initClicks() {
        rl_FinalEvaluation_open.setOnClickListener {
            openFinalEvaluationOpenFragment()
        }

        rl_FinalEvaluation_closed.setOnClickListener {
            openFinalEvaluationClosedFragment()
        }
    }

    private fun initView() {
        tv_count1.text = openCount.toString()
        tv_count2.text = closedCount.toString()
    }


    private fun openFinalEvaluationClosedFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_FinalEvaluation, finalEvaluationClosedFragment)
        transaction.commit()
    }
    private fun openFinalEvaluationOpenFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_FinalEvaluation, finalEvaluationOpenFragment)
        transaction.commit()
    }

}
