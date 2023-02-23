package com.example.malika

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    fun getAllItem(): LiveData<List<Item>>

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    fun getAllItemSynchronous(): List<Item>

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllItems()

    @Query("SELECT SUM(price*amount) FROM cart_table")
    fun getTotalPrice(): LiveData<Int>

    @Query("SELECT * FROM cart_table WHERE name LIKE :nameQuery AND price = :priceQuery LIMIT 1")
    fun getByNameAndPrice(nameQuery: String, priceQuery: Int): Item
}