package com.example.upstartswork.model

import com.example.upstartswork.data.OperationCallback

interface GoodsDataSource {
    fun retrieveGoods(callback: OperationCallback<CardViewItem>)
    fun cancel()
}