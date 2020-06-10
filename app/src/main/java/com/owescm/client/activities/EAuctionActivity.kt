package com.owescm.client.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owescm.client.fragment.EAuctionFragment.EAuctionClosedFragment
import com.owescm.client.fragment.EAuctionFragment.EAuctionTodayFragment
import com.owescm.client.fragment.EAuctionFragment.EAuctionUpcomingFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_e_auction.*

class EAuctionActivity : AppCompatActivity() {
    val eAuctionTodayFragment = EAuctionTodayFragment()
    val eAuctionUpcomingFragment = EAuctionUpcomingFragment()
    val eAuctionClosedFragment = EAuctionClosedFragment()
    var from =""
    var todayCount = 0
    var upcomingCount = 0
    var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_auction)
        from = intent.getStringExtra("from")?:""
        todayCount = intent.getIntExtra("TodayCount",-1)
        upcomingCount = intent.getIntExtra("UpcomingCount",-1)
        closedCount = intent.getIntExtra("ClosedCount",-1)
        openFragment()
        initView()

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

    private fun openFragment() {
        when (from) {
            "Today" -> openEAuctionTodayFragment()
            "Upcoming" -> openEAuctionUpcomingFragment()
            "Closed" -> openEAuctionClosedFragment()
        }
    }
    private fun initView() {
        tv_count1.text = todayCount.toString()
        tv_count2.text = upcomingCount.toString()
        tv_count3.text = closedCount.toString()
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

}
