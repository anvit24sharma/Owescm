package com.owescm.supplier.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owescm.supplier.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        txt_SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        txt_forgot_password.setOnClickListener {
            startActivity(Intent(this,
                ForgotPasswordActivity::class.java))
        }

    }
}

