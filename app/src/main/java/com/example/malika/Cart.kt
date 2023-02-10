package com.example.malika

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "cart_table")
data class Cart constructor(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "price") val price: BigDecimal?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "amount") val amount: Int?
) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}