package com.example.malika

interface OnMenuItemUpdateListener {

    fun OnItemUpdated(item: MenuItem, position: Int)

    fun onItemDecrease(item: MenuItem, position: Int)
}