package com.example.ktorsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _resultData = MutableLiveData<String>()
    val resultData: LiveData<String>
        get() = _resultData

    private val _movieData = MutableLiveData<List<DataEntity>>()
    val movieData: LiveData<List<DataEntity>>
        get() = _movieData

    fun getMovieList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = ApiClient().requestMoveSearch("red")
            _resultData.value = result
        }
    }

    fun getDataPost() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = ApiClient().requestMoveSearchPost(ApiRequest("red"))
            _resultData.value = response
        }
    }

    fun setMovieData() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = ApiClient().requestMoveSearchData("red")
            _movieData.value = response
        }
    }
}