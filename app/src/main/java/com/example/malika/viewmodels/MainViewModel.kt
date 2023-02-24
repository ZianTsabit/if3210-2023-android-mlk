package com.example.malika

import androidx.lifecycle.ViewModel

enum class PageFragmentEnum {
    Twibbon,
    Branch,
    Menu,
    Cart,
}

class MainViewModel : ViewModel() {
    var currentPage : PageFragmentEnum = PageFragmentEnum.Menu
}