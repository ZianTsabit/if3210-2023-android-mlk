package com.example.malika

import com.google.gson.annotations.SerializedName

data class MenuResponse (
    @field:SerializedName("data")
    val data: List<MenuItem>? = null,

    @field:SerializedName("size")
    val size: Int? = null,
)