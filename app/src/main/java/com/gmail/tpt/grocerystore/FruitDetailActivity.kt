package com.gmail.tpt.grocerystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fruit_detail.*
import kotlinx.android.synthetic.main.activity_test_detail.*
import kotlinx.android.synthetic.main.item_cart.view.*

class FruitDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FRUIT = "EXTRA_FRUIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        postponeEnterTransition()

        val fruit = intent.getParcelableExtra<Fruit>(EXTRA_FRUIT)

        if (fruit != null) {

            img.setImageResource(fruit.image)
            txtName.text = fruit.name
            txtWeight.text = "${fruit.weight}g"
            txtPrice.text = "$${fruit.price}"
            txtAboutMsg.text = fruit.description

        }
        startPostponedEnterTransition()

        imgBack.setOnClickListener {
            finishAfterTransition()
        }
    }
}
