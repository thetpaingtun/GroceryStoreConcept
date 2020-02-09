package com.gmail.tpt.grocerystore

import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.shape.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Fruits and vegetables")


        setupGroceryAdapter()

        setupCartAdapter()

/*        cardCart.shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setAllCornerSizes(32f)
            .build()*/


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

    private fun setupGroceryAdapter() {
        val groceryAdapter = GroceryAdapter(getGroceryList())
        val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.apply {
            adapter = groceryAdapter
            layoutManager = lm

            addItemDecoration(SpacesItemDecoration(context))
        }

        groceryAdapter.listener = {
            Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
