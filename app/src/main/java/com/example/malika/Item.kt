package com.example.malika

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "cart_table")
data class Item constructor(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "price") val price: BigDecimal?,
    @ColumnInfo(name = "amount") val amount: Int?
) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}