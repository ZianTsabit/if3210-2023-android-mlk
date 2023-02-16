package com.example.malika

import android.view.View

interface OnItemUpdateListener {

    fun OnItemUpdated(item: Item, position: Int)

    fun onItemDecrease(item: Item, position: Int)

}