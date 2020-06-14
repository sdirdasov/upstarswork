package com.example.upstartswork.model

import java.io.Serializable

data class CardViewItem(
    val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    val new: Boolean
) : Serializable