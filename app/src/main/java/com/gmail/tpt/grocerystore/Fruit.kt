package com.gmail.tpt.grocerystore

import android.os.Parcel
import android.os.Parcelable

data class Fruit(
    val name: String,
    val image: Int,
    val price: Float,
    val weight: Int,
    val description: String = "Some descriptions"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeFloat(price)
        parcel.writeInt(weight)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruit> {
        override fun createFromParcel(parcel: Parcel): Fruit {
            return Fruit(parcel)
        }

        override fun newArray(size: Int): Array<Fruit?> {
            return arrayOfNulls(size)
        }
    }
}


fun getFruitList(): List<Fruit> {
    return listOf(
        Fruit("Orange", R.drawable.oranges, 9.10f, 1000),
        Fruit("Strawberry", R.drawable.strawberry, 12.50f, 500),
        Fruit("Avocado", R.drawable.avocado, 13.45f, 500),
        Fruit("Banana", R.drawable.banana, 6.25f, 1000),
        Fruit("Mango", R.drawable.mango, 7.55f, 500),
        Fruit("Apple", R.drawable.apple, 5.50f, 500),
        Fruit("Orange", R.drawable.oranges, 9.10f, 1000),
        Fruit("Strawberry", R.drawable.strawberry, 12.50f, 500),
        Fruit("Avocado", R.drawable.avocado, 13.45f, 500),
        Fruit("Banana", R.drawable.banana, 6.25f, 1000),
        Fruit("Mango", R.drawable.mango, 7.55f, 500),
        Fruit("Apple", R.drawable.apple, 5.50f, 500)

    )
}