package com.example.upstartswork.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upstartswork.R
import com.example.upstartswork.model.CardViewItem
import kotlinx.android.synthetic.main.cardview_item.view.*

class GoodsListAdapter(private var goods: List<CardViewItem>)
    : RecyclerView.Adapter<GoodsListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(goods[position])
    }

    override fun getItemCount() : Int {
        return goods.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewTitle: TextView = view.textViewTitle
        private val imageView: ImageView = view.imageView
        private val textViewNew: TextView = view.textViewNew
        private val textViewPrice: TextView = view.textViewPrice

        fun bind(cardViewItem: CardViewItem) {
            textViewTitle.text = cardViewItem.title
            textViewPrice.text = "${cardViewItem.price} Р"
            when (cardViewItem.new) {
                true -> textViewNew.text = "NEW"
                else -> textViewNew.text = ""
            }
            Glide.with(imageView.context).load(cardViewItem.image).into(imageView)
        }
    }

    fun update(data: List<CardViewItem>) {
        goods = data
        notifyDataSetChanged()
    }
}