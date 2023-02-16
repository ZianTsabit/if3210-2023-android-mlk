package com.example.malika.api

import com.example.malika.PaymentStatus
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface MalikaApi {

    @POST("/v1/payment/{transaction_id}")
    suspend fun getPaymentStatus(
        @Path("transaction_id", encoded = true) path: String
    ): Response<PaymentStatus>


}