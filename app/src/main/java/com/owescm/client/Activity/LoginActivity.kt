package com.owescm.client.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.MainActivity
import com.owescm.client.R
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
