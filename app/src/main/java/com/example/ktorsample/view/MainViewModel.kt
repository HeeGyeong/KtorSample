package com.example.ktorsample.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ktorsample.api.ApiInterface
import com.example.ktorsample.model.ApiRequest
import com.example.ktorsample.model.DataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    private val _resultData = MutableLiveData<String>()
    val resultData: LiveData<String>
        get() = _resultData

    private val _movieData = MutableLiveData<List<DataEntity>>()
    val movieData: LiveData<List<DataEntity>>
        get() = _movieData

    fun getMovieList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiInterface.requestMoveSearch("red")
            _resultData.value = result
        }
    }

    fun getDataPost() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = apiInterface.requestMoveSearchPost(ApiRequest("red"))
            _resultData.value = response
        }
    }

    fun setMovieData() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = apiInterface.requestMoveSearchData("red")
            _movieData.value = response
        }
    }
}