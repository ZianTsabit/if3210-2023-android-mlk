package com.example.malika

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application): AndroidViewModel(application){

    val readAllItem: LiveData<List<Item>>
    private val repository: CartRepository

    init {
        val cartDao = AppDatabase.buildDatabase(application).getCartDao()
        repository = CartRepository(cartDao)
        readAllItem = repository.getAllItem
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

}