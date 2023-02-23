package com.example.malika

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BranchViewModel : ViewModel() {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    private val _branchList = MutableLiveData(ArrayList<BranchItem>())
    val branchList: LiveData<ArrayList<BranchItem>>
        get() = _branchList

    fun getBranch() {
        viewModelScope.launch {
            val response = retrofitRepository.getBranch()
            if (response.isSuccessful) {
                val list = response.body()?.data
                _branchList.value = list as ArrayList<BranchItem>
                Log.i("BRANCH", "Get branch successful")
            } else {
                Log.e("ERROR", "Response failed")
            }
        }
    }
}