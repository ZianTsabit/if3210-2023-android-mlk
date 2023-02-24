package com.example.malika.repository

import com.example.malika.api.PaymentStatus
import com.example.malika.api.BranchResponse
import com.example.malika.api.MenuResponse
import com.example.malika.api.RetrofitInstance
import retrofit2.Response

class RetrofitRepository {

    suspend fun getPaymentStatus(transactionId: String): Response<PaymentStatus> {
        return RetrofitInstance.api.getPaymentStatus(transactionId)
    }

    suspend fun getMenu(): Response<MenuResponse> {
        return RetrofitInstance.api.getMenu()
    }

    suspend fun getBranch(): Response<BranchResponse> {
        return RetrofitInstance.api.getBranch()
    }
}