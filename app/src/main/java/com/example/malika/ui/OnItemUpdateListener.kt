package com.example.malika.ui

import com.example.malika.database.Item

interface OnItemUpdateListener {

    fun OnItemUpdated(item: Item, position: Int)

    fun onItemDecrease(item: Item, position: Int)

}