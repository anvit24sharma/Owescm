package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import com.owescm.supplier.fragment.EBidClosedFragment
import com.owescm.supplier.fragment.EBidLiveFragment
import com.owescm.supplier.fragment.EBidNewFragment
import com.owescm.supplier.fragment.EBidSavedFragment
import kotlinx.android.synthetic.main.activity_e_bid.*

class EBidActivity : AppCompatActivity() {

    val ebidNewFragment = EBidNewFragment()
    val ebidLiveFragment = EBidLiveFragment()
    val ebidSavedFragment = EBidSavedFragment()
    val ebidClosedFragment = EBidClosedFragment()
    var from = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_bid)

        supportActionBar?.title = "eBid"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        from = intent.getStringExtra("from") ?: ""
        openFragment()
        initClicks()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment() {
        when (from) {
            "New" -> openEbidNewFragment()
            "Saved" -> openEbidSavedFragment()
            "Live" -> openEbidLiveFragment()
            "Closed" -> openEbidClosedFragment()
        }
    }

    private fun initClicks() {
        rL_ebidNew.setOnClickListener {
            openEbidNewFragment()
        }
        rL_ebidLive.setOnClickListener {
            openEbidLiveFragment()
        }
        rL_ebidSaved.setOnClickListener {
            openEbidSavedFragment()
        }
        rL_ebidClosed.setOnClickListener {
            openEbidClosedFragment()
        }
    }

    private fun openEbidClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Ebid, ebidClosedFragment)
        transaction.commit()
    }

    private fun openEbidSavedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Ebid, ebidSavedFragment)
        transaction.commit()
    }

    private fun openEbidLiveFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Ebid, ebidLiveFragment)
        transaction.commit()
    }

    private fun openEbidNewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Ebid, ebidNewFragment)
        transaction.commit()
    }
}
