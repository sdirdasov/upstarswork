package com.example.upstartswork.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.upstartswork.R
import com.example.upstartswork.di.Injection
import com.example.upstartswork.model.CardViewItem
import com.example.upstartswork.viewmodel.GoodsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: GoodsViewModel
    private lateinit var adapter: GoodsListAdapter

    private val renderGoods = Observer<List<CardViewItem>> {
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        textViewSearchGoods.text = "Найдено: ${it.size} товаров"
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        progressBar.visibility = when(it) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private val onMessageErrorObserver = Observer<Any> {
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(GoodsViewModel::class.java)
        viewModel.goods.observe(this, renderGoods)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)

        adapter = GoodsListAdapter(viewModel.goods.value?: emptyList())
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }
}