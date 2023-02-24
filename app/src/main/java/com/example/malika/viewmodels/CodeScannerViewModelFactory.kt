package com.example.malika.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.malika.CodeScannerViewModel
import com.example.malika.repository.RetrofitRepository

class CodeScannerViewModelFactory(private val repository: RetrofitRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CodeScannerViewModel(repository) as T
    }
}