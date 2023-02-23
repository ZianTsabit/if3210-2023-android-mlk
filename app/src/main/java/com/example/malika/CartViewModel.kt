package com.example.malika

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application): AndroidViewModel(application){

    val readAllItem: LiveData<List<Item>>
    val totalPrice: LiveData<Int>
    private val repository: CartRepository

    init {
        val cartDao = AppDatabase.buildDatabase(application).getCartDao()
        repository = CartRepository(cartDao)
        readAllItem = repository.getAllItem
        totalPrice = repository.getTotalPrice()
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun deleteAllItem(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItem()
        }
    }

    fun getByNameAndPrice(nameQuery: String, priceQuery: Int) : Item? {
        return repository.getByNameAndPrice(nameQuery, priceQuery)
    }
}