package com.example.upstartswork.di

import androidx.lifecycle.ViewModelProvider
import com.example.upstartswork.model.GoodsDataSource
import com.example.upstartswork.model.GoodsRepository
import com.example.upstartswork.viewmodel.ViewModelFactory

object Injection {
    private val goodsDataSource: GoodsDataSource = GoodsRepository()
    private val goodsViewModelFactory = ViewModelFactory(goodsDataSource)

    fun provideViewModelFactory() : ViewModelProvider.Factory {
        return goodsViewModelFactory
    }
}