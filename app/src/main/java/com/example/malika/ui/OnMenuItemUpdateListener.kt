package com.example.malika.ui

import com.example.malika.domain.MenuItem

interface OnMenuItemUpdateListener {

    fun OnItemUpdated(item: MenuItem, position: Int)

    fun onItemDecrease(item: MenuItem, position: Int)
}