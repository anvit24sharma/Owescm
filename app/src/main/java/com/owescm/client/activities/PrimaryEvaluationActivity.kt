package com.owescm.client.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.Fragment.PrimaryEvaluationFragment.PrimaryEvaluationClosedFragment
import com.owescm.client.Fragment.PrimaryEvaluationFragment.PrimaryEvaluationOpenFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_primary_evaluation.*

class PrimaryEvaluationActivity : AppCompatActivity() {
    val primaryEvaluationFragment = PrimaryEvaluationOpenFragment()
    val primaryEvaluationOpenFragment = PrimaryEvaluationOpenFragment()
    val primaryEvaluationClosedFragment = PrimaryEvaluationClosedFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_evaluation)

        openPrimaryEvaluationFragment()
        rl_PrimaryEvaluation_open.setOnClickListener {
            openPrimaryEvaluationOpenFragment()
        }
        rl_PrimaryEvaluation_closed.setOnClickListener {
            openPrimaryEvaluationClosedFragment()
        }
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

    private fun openPrimaryEvaluationFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_PrimaryEvaluation, primaryEvaluationFragment)
        transaction.commit()
    }
}
