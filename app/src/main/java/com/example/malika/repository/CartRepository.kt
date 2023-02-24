package com.example.malika.repository

import androidx.lifecycle.LiveData
import com.example.malika.database.CartDao
import com.example.malika.database.Item

class CartRepository(private val cartDao: CartDao) {

    val getAllItem: LiveData<List<Item>> = cartDao.getAllItem()

    suspend fun getAllItemSynchronous(): List<Item> {
        return cartDao.getAllItemSynchronous()
    }
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

    fun getByNameAndPrice(nameQuery: String, priceQuery: Int) : Item {
        return cartDao.getByNameAndPrice(nameQuery, priceQuery)
    }

}