package com.owescm


import android.app.Application
import com.owescm.remote.OwescmRemoteEndPoint
import com.owescm.services.repository.OwescmRepository
import com.owescm.remote.NetworkModule

import retrofit2.Retrofit

private var application: OwescmApplication? = null

class OwescmApplication : Application() {

    companion object{
        private lateinit var retrofit: Retrofit
        private lateinit var owescmRemoteEndPoint: OwescmRemoteEndPoint

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
    }

}