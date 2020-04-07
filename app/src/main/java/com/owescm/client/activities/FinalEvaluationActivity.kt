package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.Fragment.FinalEvaluationFragment.FinalEvaluationClosedFragment
import com.owescm.client.Fragment.FinalEvaluationFragment.FinalEvaluationOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_contract.*
import kotlinx.android.synthetic.main.activity_final_evaluation.*

class FinalEvaluationActivity : AppCompatActivity() {
    val finalEvaluationFragment = FinalEvaluationOpenFragment()
    val finalEvaluationOpenFragment = FinalEvaluationOpenFragment()
    val finalEvaluationClosedFragment = FinalEvaluationClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_evaluation)
        openFinalEvaluationFragment()
        rl_FinalEvaluation_open.setOnClickListener {
            openFinalEvaluationOpenFragment()
        }
        rl_FinalEvaluation_closed.setOnClickListener {
            openFinalEvaluationClosedFragment()
        }
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

    private fun openFinalEvaluationFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_FinalEvaluation, finalEvaluationFragment)
        transaction.commit()
    }
}
