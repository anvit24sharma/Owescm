package com.owescm.supplier.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()

    }
}


