package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.EAuctionClosedFragment
import com.owescm.supplier.fragment.EAuctionTodayFragment
import com.owescm.supplier.fragment.EAuctionUpcomingFragment
import kotlinx.android.synthetic.main.activity_e_auction.*

class EAuctionActivity : AppCompatActivity() {
    val eAuctionFragment = EAuctionTodayFragment()
    val eAuctionTodayFragment = EAuctionTodayFragment()
    val eAuctionUpcomingFragment = EAuctionUpcomingFragment()
    val eAuctionClosedFragment = EAuctionClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_auction)

        supportActionBar?.title = "eAuction"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        openEAuctionFragment()
        rl_EAuction_today.setOnClickListener {
            openEAuctionTodayFragment()
        }
        rl_EAuction_upcoming.setOnClickListener {
            openEAuctionUpcomingFragment()
        }
        rl_EAuction_closed.setOnClickListener {
            openEAuctionClosedFragment()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openEAuctionClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction, eAuctionClosedFragment)
        transaction.commit()

    }

    private fun openEAuctionUpcomingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction, eAuctionUpcomingFragment)
        transaction.commit()

    }

    private fun openEAuctionTodayFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction, eAuctionTodayFragment)
        transaction.commit()

    }

    private fun openEAuctionFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction, eAuctionFragment)
        transaction.commit()
    }
}

