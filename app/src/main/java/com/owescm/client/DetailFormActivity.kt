package com.owescm.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_form.*

class DetailFormActivity : AppCompatActivity() {
    val erfxNewfragment = ErfxNewFragment()
    val erfxSavedfragment = ErfxSavedFragment()
    val erfxLivefragment = ErfxLiveFragment()
    val erfxClosedfragment = ErfxClosedFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_form)
        openErfxNewFragment()

        rL_erfxNew.setOnClickListener {
            openErfxNewFragment()
        }
        rL_erfxSaved.setOnClickListener {
            openErfxSavedFragment()
        }
        rL_erfxLive.setOnClickListener {
            openErfxLiveFragment()

        }
        rL_erfxClosed.setOnClickListener {
            openErfxClosedFragment()
        }


    }

    private fun openErfxNewFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, erfxNewfragment)
        transaction.commit()
    }

    private fun openErfxSavedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, erfxSavedfragment)
        transaction.commit()
    }

    private fun openErfxLiveFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, erfxLivefragment)
        transaction.commit()
    }

    private fun openErfxClosedFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, erfxClosedfragment)
        transaction.commit()
    }


}
