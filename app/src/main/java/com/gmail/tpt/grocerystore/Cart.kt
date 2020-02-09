package com.gmail.tpt.grocerystore

data class Cart(val drawable: Int, val count: Int, val name: String, val totalPrice: Float)


fun getCartList(): List<Cart> {
    return listOf(
        Cart(R.drawable.oranges, 1, "Orange", 8.30f),
        Cart(R.drawable.pineapple, 2, "Pineapple", 10.60f),
        Cart(R.drawable.strawberry, 1, "Strawberry", 15.10f)

    )
}