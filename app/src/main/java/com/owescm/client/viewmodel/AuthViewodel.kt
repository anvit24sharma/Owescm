package com.owescm.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.client.model.LoginRequest
import com.owescm.client.model.LoginResponse

class AuthViewodel :ViewModel(){

    lateinit var loginLiveData :LiveData<LoginResponse>

    fun login(loginRequest : HashMap<String,String>){
        loginLiveData = Transformations.map(getOwescmRepository().doLogin(loginRequest)){
            return@map it.responseData as LoginResponse
        }
    }
}