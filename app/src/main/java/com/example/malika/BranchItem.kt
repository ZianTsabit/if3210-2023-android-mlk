package com.example.malika

import com.google.gson.annotations.SerializedName

data class BranchItem(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("popular_food")
    val popularFood: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("contact_person")
    val contactPerson: String,

    @field:SerializedName("phone_number")
    val phoneNumber: String,

    @field:SerializedName("langitude")
    val langitude: Double,

    @field:SerializedName("latitude")
    val latitude: Double,
)
