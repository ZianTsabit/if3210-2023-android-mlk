package com.example.malika.api

import com.example.malika.PaymentStatus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.18.30:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MalikaApi by lazy {
        retrofit.create(MalikaApi::class.java)
    }

}