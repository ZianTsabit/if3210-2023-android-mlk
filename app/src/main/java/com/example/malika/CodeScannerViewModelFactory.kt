package com.example.malika

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CodeScannerViewModelFactory(private val repository: RetrofitRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CodeScannerViewModel(repository) as T
    }
}