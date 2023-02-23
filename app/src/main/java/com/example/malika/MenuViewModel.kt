package com.example.malika

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()
    lateinit var foodList : ArrayList<MenuItem>
    lateinit var drinkList : ArrayList<MenuItem>
    var searchQuery : String = ""

    fun getMenu() {
        viewModelScope.launch {
            val response = retrofitRepository.getMenu()
            if (response.isSuccessful) {
                val list = response.body()?.data
                foodList = list?.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
                drinkList  = list?.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>
                Log.i("LIST", foodList.toString())
            } else {
                Log.e("ERROR", "Response failed")
            }
        }
    }
}