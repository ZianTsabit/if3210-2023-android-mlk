package com.example.malika

import androidx.lifecycle.LiveData

class CartRepository(private val cartDao: CartDao) {

    val getAllItem: LiveData<List<Item>> = cartDao.getAllItem()
    suspend fun addItem(item: Item) {
        cartDao.addItem(item)
    }

    suspend fun updateItem(item: Item) {
        cartDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        cartDao.deleteItem(item)
    }

    suspend fun deleteAllItem() {
        cartDao.deleteAllItems()
    }

    fun getTotalPrice(): LiveData<Int> {
        return cartDao.getTotalPrice()
    }

}