package com.example.malika

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CodeScannerViewModel(private val retrofitRepository: RetrofitRepository): ViewModel() {

    val currentPaymentStatus : MutableLiveData<Response<PaymentStatus>> = MutableLiveData()

    fun getPaymentStatus(transactionId: String) {
        viewModelScope.launch {
           try {
               val response = retrofitRepository.getPaymentStatus(transactionId)
               currentPaymentStatus.value = response
           }catch (e: Exception){
               Log.e("PAYMENT STATUS", "Connection failed")
           }
        }
    }
}