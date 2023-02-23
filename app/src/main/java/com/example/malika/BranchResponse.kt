package com.example.malika

import com.google.gson.annotations.SerializedName

data class BranchResponse(
    @field:SerializedName("data")
    val data: List<BranchItem>? = null,

    @field:SerializedName("size")
    val size: Int? = null,
)
