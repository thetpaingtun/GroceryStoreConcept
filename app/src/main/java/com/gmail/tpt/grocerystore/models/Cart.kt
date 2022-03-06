package com.gmail.tpt.grocerystore.models

import com.gmail.tpt.grocerystore.R

data class Cart(
    val drawable: Int = R.drawable.oranges,
    val count: Int = 0,
    val name: String = "",
    val totalPrice: Float = 0f
)


fun getCartList(): List<Cart> {
    return listOf(
        Cart(R.drawable.oranges, 1, "Orange", 8.30f),
        Cart(R.drawable.strawberry, 1, "Strawberry", 15.10f)

    )
}