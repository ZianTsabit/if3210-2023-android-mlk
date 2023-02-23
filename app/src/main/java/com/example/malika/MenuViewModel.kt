package com.example.malika

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    private var _wholeFoodList = ArrayList<MenuItem>()
    private var _wholeDrinkList = ArrayList<MenuItem>()

    private val _foodList = MutableLiveData(_wholeFoodList)
    val foodList: LiveData<ArrayList<MenuItem>>
        get() = _foodList

    private val _drinkList = MutableLiveData(_wholeDrinkList)
    val drinkList: LiveData<ArrayList<MenuItem>>
        get() = _drinkList

    var searchQuery : String = ""

    fun getMenu() {
        viewModelScope.launch {
            val response = retrofitRepository.getMenu()
            if (response.isSuccessful) {
                val list = response.body()?.data

                _wholeFoodList = list?.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
                _wholeDrinkList = list?.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>

                _foodList.value = list?.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
                _drinkList.value  = list?.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>

                Log.i("MENU", "Get menu successful")
            } else {
                Log.e("ERROR", "Response failed")
            }
        }
    }

    fun search() {
        val regex = searchQuery.lowercase().toRegex()

        _foodList.value = _wholeFoodList.filter { s -> regex.find(s.name.lowercase()) != null } as ArrayList<MenuItem>
        _drinkList.value = _wholeDrinkList.filter { s -> regex.find(s.name.lowercase()) != null } as ArrayList<MenuItem>

    }
}