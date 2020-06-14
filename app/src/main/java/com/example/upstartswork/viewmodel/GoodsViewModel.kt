package com.example.upstartswork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upstartswork.data.OperationCallback
import com.example.upstartswork.model.GoodsDataSource
import com.example.upstartswork.model.CardViewItem

class GoodsViewModel(private val repository: GoodsDataSource) : ViewModel() {
    private val _goods = MutableLiveData<List<CardViewItem>>()
        .apply { value = emptyList() }
    val goods: LiveData<List<CardViewItem>> = _goods

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun load() {
        _isViewLoading.postValue(true)
        repository.retrieveGoods(object : OperationCallback<CardViewItem>{
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }

            override fun onSuccess(data: List<CardViewItem>?) {
                _isViewLoading.postValue(false)
                if (data != null) {
                    if (data.isEmpty())
                        _isEmptyList.postValue(true)
                    else
                        _goods.value = data
                }
            }
        })
    }
}