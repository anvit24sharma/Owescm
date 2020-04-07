package com.owescm.client.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owescm.client.MainActivity
import com.owescm.client.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        btn_SignUp.setOnClickListener {
            startActivity((Intent(this, MainActivity::class.java)))
            finish()
        }
    }
}
