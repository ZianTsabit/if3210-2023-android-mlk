package com.example.malika.api

import com.example.malika.BranchResponse
import com.example.malika.MenuResponse
import com.example.malika.PaymentStatus
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MalikaApi {

    @POST("/v1/payment/{transaction_id}")
    suspend fun getPaymentStatus(
        @Path("transaction_id", encoded = true) path: String
    ): Response<PaymentStatus>

    @GET("/v1/menu")
    suspend fun getMenu(): Response<MenuResponse>

    @GET("/v1/branch")
    suspend fun getBranch(): Response<BranchResponse>


}