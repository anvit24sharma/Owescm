package com.owescm.client.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.MainActivity
import com.owescm.client.R
import com.owescm.client.viewmodel.AuthViewodel
import com.owescm.services.repository.OwescmRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var authViewodel :AuthViewodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        authViewodel = ViewModelProviders.of(this).get(AuthViewodel::class.java)



        txt_SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        btn_login.setOnClickListener {
            login()
        }
        txt_forgot_password.setOnClickListener {
            startActivity(Intent(this,
                ForgotPasswordActivity::class.java))
        }

    }

    private fun login() {

        val email :String = et_email.text.toString()
        val password :String = et_password.text.toString()

        val map = HashMap<String,String>()
        map["email"] = email
        map["password"] = password
        map["api_key"] = apiKey
        map["user_type"] = userType

        authViewodel.login(map)
        startActivity(Intent(this, MainActivity::class.java))

    }
}
