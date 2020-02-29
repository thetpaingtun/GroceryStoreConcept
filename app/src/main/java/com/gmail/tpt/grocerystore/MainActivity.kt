package com.gmail.tpt.grocerystore

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val RC_DETAIL = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Fruits and vegetables")


        setupMainAdapter()

        setupCartAdapter()

    }


    private fun setupCartAdapter() {
        val cartAdapter = CartAdapter(getCartList())
        val lm = LinearLayoutManager(this)

        recyCart.apply {
            adapter = cartAdapter
            layoutManager = lm

            addItemDecoration(SpacesItemDecoration(context))
        }
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
            ViewCompat.getTransitionName(imgView)
        )

        val intent = Intent(this, FruitDetailActivity::class.java).apply {
            putExtra(FruitDetailActivity.EXTRA_FRUIT, fruit)
        }

        startActivityForResult(
            intent,
            RC_DETAIL,
            options.toBundle()
        )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
