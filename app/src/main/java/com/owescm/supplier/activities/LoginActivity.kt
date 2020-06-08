package com.owescm.supplier.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.owescm.OwescmApplication
import com.owescm.supplier.R
import com.owescm.utils.Constants
import com.owescm.viewmodel.AuthViewodel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var authViewodel: AuthViewodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        if (OwescmApplication.prefs.getString(Constants.EMAIL, "") ?: "" != "") {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        authViewodel = ViewModelProviders.of(this).get(AuthViewodel::class.java)

        txt_SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        btn_login.setOnClickListener {
            login()
        }
        txt_forgot_password.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ForgotPasswordActivity::class.java
                )
            )
        }

    }

    private fun login() {

        val email: String = et_email.text.toString()
        val password: String = et_password.text.toString()

        val map = HashMap<String, String>()
        map["email"] = email
        map["password"] = password
        map["api_key"] = OwescmApplication.apiKey
        map["user_type"] = OwescmApplication.userType

        authViewodel.login(map).observe(this, Observer {
            if (it.message == "user logged in successfully") {
                val prefs = OwescmApplication.prefs.edit()
                prefs.putString(Constants.EMAIL, it.data.email)
                prefs.putString(Constants.PROFILE_IMAGE, it.data.profilePhoto)
                prefs.putString(Constants.MOBILE_NUMBER, it.data.mobileNo)
                prefs.putString(Constants.DESIGNATION, it.data.designation)
                prefs.putString(Constants.USER_ID, it.data.user_id)
                prefs.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
