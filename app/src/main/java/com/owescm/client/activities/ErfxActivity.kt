package com.owescm.client.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owescm.client.Fragment.ErfxFragment.ErfxClosedFragment
import com.owescm.client.Fragment.ErfxFragment.ErfxLiveFragment
import com.owescm.client.Fragment.ErfxFragment.ErfxNewFragment
import com.owescm.client.Fragment.ErfxFragment.ErfxSavedFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_erfx.*

class ErfxActivity : AppCompatActivity() {

    val erfxFragment=ErfxNewFragment()
    val erfxNewFragment = ErfxNewFragment()
    val erfxLiveFragment = ErfxLiveFragment()
    val erfxSavedFragment = ErfxSavedFragment()
    val erfxClosedFragment = ErfxClosedFragment()
    var from =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erfx)

        supportActionBar?.title = "eRfx"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        from = intent.getStringExtra("from")?:""
        openFragment()
        initClicks()


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

    private fun openErfxFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_Erfx, erfxFragment)
        transaction.commit()    }

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
