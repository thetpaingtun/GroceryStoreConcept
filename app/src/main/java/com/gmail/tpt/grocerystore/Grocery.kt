package com.gmail.tpt.grocerystore

data class Grocery(val name: String, val image: Int, val price: Float, val weight: Int)


fun getGroceryList(): List<Grocery> {
    return listOf(
        Grocery("Orange", R.drawable.oranges, 9.10f, 1000),
        Grocery("Strawberry", R.drawable.strawberry, 12.50f, 500),
        Grocery("Avocado", R.drawable.avocado, 13.45f, 500),
        Grocery("Banana", R.drawable.banana, 6.25f, 1000),
        Grocery("Mango", R.drawable.mango, 7.55f, 500),
        Grocery("Pineapple", R.drawable.pineapple, 6.20f, 500),
        Grocery("Orange", R.drawable.oranges, 9.10f, 1000),
        Grocery("Strawberry", R.drawable.strawberry, 12.50f, 500),
        Grocery("Avocado", R.drawable.avocado, 13.45f, 500),
        Grocery("Banana", R.drawable.banana, 6.25f, 1000),
        Grocery("Mango", R.drawable.mango, 7.55f, 500),
        Grocery("Pineapple", R.drawable.pineapple, 6.20f, 500)

    )
}