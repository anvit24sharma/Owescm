package com.owescm


import android.app.Application
import com.owescm.remote.NetworkModule
import com.owescm.remote.OwescmRemoteEndPoint
import retrofit2.Retrofit

private var application: OwescmApplication? = null

class OwescmApplication : Application() {

    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var prydeRemoteEndPoint: OwescmRemoteEndPoint

        //    fun getPrydeRepository(): PrydeRepository = PrydeRepository(prydeRemoteEndPoint)

        fun getApplicationContext(): OwescmApplication {
            return application!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        retrofit = NetworkModule.getRetrofit(baseContext)
        application = this
        prydeRemoteEndPoint = retrofit.create(OwescmRemoteEndPoint::class.java)
    }

}