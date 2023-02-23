package com.example.malika

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private val _foodList = MutableLiveData(ArrayList<MenuItem>())
    val foodList: LiveData<ArrayList<MenuItem>>
        get() = _foodList

    private val _drinkList = MutableLiveData(ArrayList<MenuItem>())
    val drinkList: LiveData<ArrayList<MenuItem>>
        get() = _drinkList

    var searchQuery : String = ""

    fun getMenu() {
        viewModelScope.launch {
            val response = retrofitRepository.getMenu()
            if (response.isSuccessful) {
                val list = response.body()?.data
                _foodList.value = list?.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
                _drinkList.value  = list?.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>
                Log.i("MENU", "Get menu successful")
            } else {
                Log.e("ERROR", "Response failed")
            }
        }
    }
}