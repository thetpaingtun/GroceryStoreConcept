package com.gmail.tpt.grocerystore

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fruit_detail.*

class FruitDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ADD_TO_CART = "EXTRA_ADD_TO_CART"
        const val EXTRA_FRUIT = "EXTRA_FRUIT"
        const val EXTRA_CART_FULL = "EXTRA_CART_FULL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        postponeEnterTransition()

        window.enterTransition =
            TransitionInflater.from(this).inflateTransition(R.transition.trans_detail_content_enter)

        window.returnTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.trans_detail_content_return)

        window.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(R.transition.trans_shared_element)


        val fruit = intent.getParcelableExtra<Fruit>(EXTRA_FRUIT)
        val cartFull = intent.getBooleanExtra(EXTRA_CART_FULL, false)

        if (fruit != null) {
            txtName.text = fruit.name
            txtWeight.text = "${fruit.weight}g"
            txtPrice.text = "$${fruit.price}"
            txtAboutMsg.text = fruit.description


            img.post {
                Logger.d("detail => ${img.width}x${img.height}")
            }


            img.load(fruit.image) {
                startPostponedEnterTransition()
            }
        }

        imgBack.setOnClickListener {
            finishAfterTransition()
        }

        btnAddToCart.setOnClickListener {
            if (!cartFull) {
                img.showRoundedBackground = true
            }
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(EXTRA_ADD_TO_CART, true)
                putExtra(EXTRA_FRUIT, fruit)
            })
            finishAfterTransition()
        }

    }
}
