package com.gmail.tpt.grocerystore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionInflater
import kotlinx.android.synthetic.main.activity_fruit_detail.*
import kotlinx.android.synthetic.main.activity_test_detail.*
import kotlinx.android.synthetic.main.item_cart.view.*

class FruitDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ADD_TO_CART = "EXTRA_ADD_TO_CART"
        const val EXTRA_FRUIT = "EXTRA_FRUIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        postponeEnterTransition()


        window.sharedElementReturnTransition = ChangeBounds().apply {
            duration = 300
        }

        val fruit = intent.getParcelableExtra<Fruit>(EXTRA_FRUIT)

        if (fruit != null) {
            txtName.text = fruit.name
            txtWeight.text = "${fruit.weight}g"
            txtPrice.text = "$${fruit.price}"
            txtAboutMsg.text = fruit.description

            img.load(fruit.image) {
                startPostponedEnterTransition()
            }
        }

        imgBack.setOnClickListener {
            finishAfterTransition()
        }

        btnAddToCart.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply { putExtra(EXTRA_ADD_TO_CART, true) })
            finishAfterTransition()
        }
    }
}
