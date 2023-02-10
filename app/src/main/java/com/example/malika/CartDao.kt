package com.example.malika

import androidx.room.*

@Dao
interface CartDao {
    @Insert
    suspend fun addCart(item: Item)

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    suspend fun getAllItem(): List<Item>

    @Update
    suspend fun updateCart(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllItem()

}