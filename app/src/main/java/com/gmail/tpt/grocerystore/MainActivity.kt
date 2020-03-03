package com.gmail.tpt.grocerystore

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.*
import android.view.Menu
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tes.*

class MainActivity : AppCompatActivity() {

    private var mAddedToCart: Boolean = false
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


//        window.reenterTransition = Fade().apply { startDelay = 400 }

        window.exitTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.trans_main_content_exit)
/*
        window.sharedElementExitTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.trans_shared_element)*/


        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>
            ) {
                if (mAddedToCart) {
                    sharedElements.clear()
                    sharedElements.put(getString(R.string.transition_fruit), civFruit2)
                    mAddedToCart = false
                }

            }
        })
    }


    private fun setupCartAdapter() {
        val cartAdapter = CartAdapter(getCartList())
        val lm = LinearLayoutManager(this)

        recyCart.apply {
            adapter = cartAdapter
            layoutManager = lm

            addItemDecoration(SpacesItemDecoration(context, 32))
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

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
//        postponeEnterTransition()

        mAddedToCart = data?.getBooleanExtra(FruitDetailActivity.EXTRA_ADD_TO_CART, false) ?: false

//        Handler().postDelayed({ startPostponedEnterTransition() }, 500)
    }
}
