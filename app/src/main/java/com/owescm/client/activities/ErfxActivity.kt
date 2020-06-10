package com.owescm.client.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.client.fragment.erfx.ErfxClosedFragment
import com.owescm.client.fragment.erfx.ErfxLiveFragment
import com.owescm.client.fragment.erfx.ErfxNewFragment
import com.owescm.client.fragment.erfx.ErfxSavedFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_erfx.*

class ErfxActivity : AppCompatActivity() {

    private val erfxNewFragment = ErfxNewFragment()
    private val erfxLiveFragment = ErfxLiveFragment()
    private val erfxSavedFragment = ErfxSavedFragment()
    private val erfxClosedFragment = ErfxClosedFragment()
    var from =""
    var savedCount = 0
    var livedCount = 0
    var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erfx)

        supportActionBar?.title = "eRfx"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        from = intent.getStringExtra("from")?:""
        savedCount = intent.getIntExtra("SavedCount",-1)
        livedCount = intent.getIntExtra("LiveCount",-1)
        closedCount = intent.getIntExtra("ClosedCount",-1)

        initView()
        openFragment()
        initClicks()
    }

    private fun initView() {
        tv_count2.text = savedCount.toString()
        tv_count3.text = livedCount.toString()
        tv_count4.text = closedCount.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment() {
        when (from) {
            "New" -> openErfxNewFragment()
            "Saved" -> openErfxSavedFragment()
            "Live" -> openErfxLiveFragment()
            "Closed" -> openErfxClosedFragment()
        }
    }

    private fun initClicks() {
        rL_erfxNew.setOnClickListener{
            openErfxNewFragment()
        }
        rL_erfxLive.setOnClickListener{
            openErfxLiveFragment()
        }
        rL_erfxSaved.setOnClickListener{
            openErfxSavedFragment()
        }
        rL_erfxClosed.setOnClickListener{
            openErfxClosedFragment()
        }
    }

    private fun openErfxClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Erfx, erfxClosedFragment)
        transaction.commit()
    }

    private fun openErfxSavedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Erfx, erfxSavedFragment)
        transaction.commit()
    }

    private fun openErfxLiveFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Erfx, erfxLiveFragment)
        transaction.commit()
    }

    private fun openErfxNewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Erfx, erfxNewFragment)
        transaction.commit()
    }
}
