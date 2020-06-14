package com.example.upstartswork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upstartswork.model.GoodsDataSource
import com.example.upstartswork.model.GoodsRepository

class ViewModelFactory(
    private val repository: GoodsDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GoodsViewModel(repository) as T
    }
}