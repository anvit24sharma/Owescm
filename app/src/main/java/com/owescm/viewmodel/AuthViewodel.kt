package com.owescm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.supplier.model.LoginResponse

class AuthViewodel : ViewModel() {

    lateinit var loginLiveData: LiveData<LoginResponse>

    fun login(loginRequest: HashMap<String, String>): LiveData<LoginResponse> {
        loginLiveData = Transformations.map(getOwescmRepository().doLogin(loginRequest)) {
            return@map it.responseData as LoginResponse
        }
        return loginLiveData
    }
}