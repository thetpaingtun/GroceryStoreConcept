package com.gmail.tpt.grocerystore

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

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
//        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.apply {
            adapter = groceryAdapter
            layoutManager = lm

            addItemDecoration(SpacesItemDecoration(context))
        }

        groceryAdapter.listener = { view, grocery ->
            Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
            Logger.d("pos => " + view.getLocationOnScreen())
            initAnimate(view, grocery)
            motionRoot.setTransition(R.id.start, R.id.detailEnd)
            motionRoot.setTransitionDuration(300)
            motionRoot.transitionToEnd()
        }
    }

    private fun initAnimate(view: View, grocery: Grocery) {
//        animatedView.isClickable = visibility == View.VISIBLE
//        animatedView.isFocusable = visibility == View.VISIBLE


        motionLayoutDetail.animated_logo.setImageDrawable(
            ContextCompat.getDrawable(
                this, grocery.image
            )
        )

        val set = motionRoot.getConstraintSet(R.id.start)
        set.clear(R.id.motionLayoutDetail)
        set.constrainWidth(R.id.motionLayoutDetail, view.width)
        set.constrainHeight(R.id.motionLayoutDetail, view.height)
//        set.constrainWidth(R.id.motionLayoutDetail, view.width)
//        set.constrainHeight(R.id.motionLayoutDetail, view.height)
/*        if (view.right - view.width < 0) {
            set.connect(
                R.id.motionLayoutDetail,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                view.right
            )
        } else {
            set.connect(
                R.id.motionLayoutDetail,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                view.getLocationOnScreen().x
            )
        }*/
        set.setVisibility(R.id.motionLayoutDetail, View.VISIBLE)
//        animatedView.isClickable = false
//        animatedView.isFocusable = false
/*        set.connect(
            R.id.motionLayoutDetail,
            ConstraintSet.TOP,
            R.id.recyclerView,
            ConstraintSet.TOP,
            0
        )
        set.connect(
            R.id.motionLayoutDetail,
            ConstraintSet.START,
            R.id.recyclerView,
            ConstraintSet.START,
            view.getLocationOnScreen().x
        )*/
        set.connect(
            R.id.motionLayoutDetail,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            view.getLocationOnScreen().x
        )

        set.connect(
            R.id.motionLayoutDetail,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            view.getLocationOnScreen().y
        )
        set.applyTo(motionRoot)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
