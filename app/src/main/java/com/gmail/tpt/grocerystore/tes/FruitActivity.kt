package com.gmail.tpt.grocerystore.tes

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gmail.tpt.grocerystore.*
import kotlinx.android.synthetic.main.activity_main.*

class FruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        setupMainAdapter()
    }

    private fun setupMainAdapter() {
        val fruitAdapter = FruitAdapter(this, getFruitList())
        val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.apply {
            adapter = fruitAdapter
            layoutManager = lm

            addItemDecoration(SpacesItemDecoration(context))
        }

        fruitAdapter.listener = { imgView, grocery ->
            gotoFruitDetail(imgView, grocery)
        }
    }

    private fun gotoFruitDetail(imgView: ImageView, fruit: Fruit) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            imgView,
            "img_trans"
        )

        val intent = Intent(this, TestDetail::class.java).apply {
            putExtra(FruitDetailActivity.EXTRA_FRUIT, fruit)
        }


        startActivityForResult(
            intent,
            10,
            options.toBundle()
        )
    }
}