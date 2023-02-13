package com.example.malika

import androidx.lifecycle.LiveData

class CartRepository(private val cartDao: CartDao) {

    val getAllItem: LiveData<List<Item>> = cartDao.getAllItem()

    suspend fun addItem(item: Item) {
        cartDao.addItem(item)
    }
}