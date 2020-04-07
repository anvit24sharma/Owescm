package com.owescm.client.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.Fragment.EAuctionFragment.EAuctionClosedFragment
import com.owescm.client.Fragment.EAuctionFragment.EAuctionTodayFragment
import com.owescm.client.Fragment.EAuctionFragment.EAuctionUpcomingFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_e_auction.*

class EAuctionActivity : AppCompatActivity() {
    val eAuctionFragment=EAuctionTodayFragment()
    val eAuctionTodayFragment = EAuctionTodayFragment()
    val eAuctionUpcomingFragment = EAuctionUpcomingFragment()
    val eAuctionClosedFragment = EAuctionClosedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_auction)
        openFragments()

    }

    private fun openFragments() {
        openEAuctionFragment()
        rl_EAuction_today.setOnClickListener{
            openEAuctionTodayFragment()
        }
        rl_EAuction_upcoming.setOnClickListener{
            openEAuctionUpcomingFragment()
        }
        rl_EAuction_closed.setOnClickListener{
            openEAuctionClosedFragment()
        }

    }

    private fun openEAuctionClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction, eAuctionClosedFragment)
        transaction.commit()    }

    private fun openEAuctionUpcomingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_EAuction,eAuctionUpcomingFragment)
        transaction.commit()    }

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
