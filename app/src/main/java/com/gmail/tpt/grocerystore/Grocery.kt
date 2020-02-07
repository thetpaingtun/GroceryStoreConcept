package com.gmail.tpt.grocerystore

data class Grocery(val name: String, val image: Int, val price: Float, val weight: Int)


fun getGroceryList(): List<Grocery> {
    return listOf(
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500),
        Grocery("Orange", R.drawable.orange, 9.10f, 500)
    )
}