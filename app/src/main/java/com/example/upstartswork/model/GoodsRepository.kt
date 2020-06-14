package com.example.upstartswork.model

import com.example.upstartswork.data.ApiClient
import com.example.upstartswork.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoodsRepository : GoodsDataSource {
    private var call: Call<List<CardViewItem>>? = null

    override fun retrieveGoods(callback: OperationCallback<CardViewItem>) {
        call = ApiClient.build()?.getGoods()
        call?.enqueue(object : Callback<List<CardViewItem>> {
            override fun onFailure(call: Call<List<CardViewItem>>, t: Throwable) {
                callback.onError("An error occurred ${t.message}")
            }

            override fun onResponse(
                call: Call<List<CardViewItem>>, response: Response<List<CardViewItem>>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("An error occurred")
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}