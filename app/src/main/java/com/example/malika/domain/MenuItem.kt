package com.example.malika.domain

import com.google.gson.annotations.SerializedName

data class MenuItem(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("currency")
    val currency: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("sold")
    val sold: Int,

    @field:SerializedName("type")
    val type: String,

    val amount: Int = 0
)
