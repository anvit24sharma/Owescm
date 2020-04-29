package com.owescm


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.owescm.remote.OwescmRemoteEndPoint
import com.owescm.services.repository.OwescmRepository
import com.owescm.remote.NetworkModule
import com.owescm.utils.Constants.Companion.PREFS

import retrofit2.Retrofit

private var application: OwescmApplication? = null

class OwescmApplication : Application() {

    companion object{
        private lateinit var retrofit: Retrofit
        private lateinit var owescmRemoteEndPoint: OwescmRemoteEndPoint
        lateinit var prefs : SharedPreferences
        var apiKey = "97d2b7920b5ccbe36878cea391233299"
        var userType ="2"
        fun getOwescmRepository(): OwescmRepository = OwescmRepository(owescmRemoteEndPoint)

        fun getApplicationContext(): OwescmApplication {
            return application!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        retrofit = NetworkModule.getRetrofit(baseContext)
        application = this
        owescmRemoteEndPoint = retrofit.create(OwescmRemoteEndPoint::class.java)
        prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

}