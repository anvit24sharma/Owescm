package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.ScoreBoardUpdatesFragment
import com.owescm.supplier.fragment.ScoreboardCurrentFragment
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity() {
    val scoreboardFragment = ScoreboardCurrentFragment()
    val scoreboardCurrentFragment = ScoreboardCurrentFragment()
    val scoreboardUpdatesragment = ScoreBoardUpdatesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        supportActionBar?.title = "Scoreboard"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        openScoreboardFragment()
        rl_Scoreboard_current.setOnClickListener {
            openScoreboardCurrentFragment()
        }
        rl_Scoreboard_updates.setOnClickListener {
            openScoreboardUpdatesFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openScoreboardCurrentFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Scoreboard, scoreboardCurrentFragment)
        transaction.commit()
    }

    private fun openScoreboardFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Scoreboard, scoreboardFragment)
        transaction.commit()
    }

    private fun openScoreboardUpdatesFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Scoreboard, scoreboardUpdatesragment)
        transaction.commit()
    }
}

