package com.owescm.client.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.owescm.OwescmApplication.Companion.prefs
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.MainActivity
import com.owescm.client.R
import com.owescm.client.viewmodel.AuthViewodel
import com.owescm.utils.Constants.Companion.DESIGNATION
import com.owescm.utils.Constants.Companion.EMAIL
import com.owescm.utils.Constants.Companion.MOBILE_NUMBER
import com.owescm.utils.Constants.Companion.PROFILE_IMAGE
import com.owescm.utils.Constants.Companion.USER_ID
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.collections.HashMap

class LoginActivity : AppCompatActivity() {

    lateinit var authViewodel :AuthViewodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        if( prefs.getString(EMAIL, "")?:"" !=""){
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

        authViewodel.login(map).observe(this, Observer {
            if(it.message == "user logged in successfully") {
                val prefs = prefs.edit()
                prefs.putString(EMAIL, it.data.email)
                prefs.putString(PROFILE_IMAGE, it.data.profilePhoto)
                prefs.putString(MOBILE_NUMBER, it.data.mobileNo)
                prefs.putString(DESIGNATION, it.data.designation)
                prefs.putString(USER_ID, it.data.user_id)
                prefs.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}
