package com.example.malika

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    fun getAllItem(): LiveData<List<Item>>
}