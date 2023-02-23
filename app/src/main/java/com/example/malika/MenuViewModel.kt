package com.example.malika

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class MenuViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()
    private val cartRepository: CartRepository

    private var _wholeFoodList = ArrayList<MenuItem>()
    private var _wholeDrinkList = ArrayList<MenuItem>()

    private val _foodList = MutableLiveData(_wholeFoodList)
    val foodList: LiveData<ArrayList<MenuItem>>
        get() = _foodList

    private val _drinkList = MutableLiveData(_wholeDrinkList)
    val drinkList: LiveData<ArrayList<MenuItem>>
        get() = _drinkList

    var searchQuery : String = ""
    init {
        val cartDao = AppDatabase.buildDatabase(application).getCartDao()
        cartRepository = CartRepository(cartDao)
    }

    fun getMenu() {
        viewModelScope.launch {
            try {
                val response = retrofitRepository.getMenu()
                if (response.isSuccessful) {
                    val list = response.body()?.data

                    _wholeFoodList = list?.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
                    _wholeDrinkList = list?.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>

                    val itemList = cartRepository.getAllItemSynchronous()

                    updateAmount(itemList)

                    Log.i("MENU", "Get menu successful")
                } else {
                    Log.e("MENU", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("MENU", "Connection failed")
            }

        }
    }

    fun search() {
        val regex = searchQuery.lowercase().toRegex()

        _foodList.value = _wholeFoodList.filter { s -> regex.find(s.name.lowercase()) != null } as ArrayList<MenuItem>
        _drinkList.value = _wholeDrinkList.filter { s -> regex.find(s.name.lowercase()) != null } as ArrayList<MenuItem>

    }

    fun updateAmount(itemList: List<Item>) {
        _wholeFoodList = _wholeFoodList.map { s ->
            val itemInCarts = itemList.find {item -> item.name == s.name && item.price == s.price}
            if (itemInCarts == null) {
                MenuItem(s.name, s.description, s.currency, s.price, s.sold, s.type, 0)
            } else {
                MenuItem(s.name, s.description, s.currency, s.price, s.sold, s.type, itemInCarts.amount)
            }
        } as ArrayList<MenuItem>

        _wholeDrinkList = _wholeDrinkList.map { s ->
            val itemInCarts = itemList.find {item -> item.name == s.name && item.price == s.price}
            if (itemInCarts == null) {
                MenuItem(s.name, s.description, s.currency, s.price, s.sold, s.type, 0)
            } else {
                MenuItem(s.name, s.description, s.currency, s.price, s.sold, s.type, itemInCarts.amount)
            }
        } as ArrayList<MenuItem>

        search()
    }
}