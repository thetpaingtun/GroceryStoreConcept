package com.gmail.tpt.grocerystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    val groceryAdapter = GroceryAdapter(getGroceryList())
    val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    recyclerView.apply {
      adapter = groceryAdapter
      layoutManager = lm

      addItemDecoration(SpacesItemDecoration(context))

    }
  }
}
