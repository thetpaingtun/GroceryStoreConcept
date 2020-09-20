package com.gmail.tpt.grocerystore

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tes.*

class MainActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private var mTopCartFull: Boolean = false
    private var mTopHoldableItemCount: Int = 0
    private var mAddedFruit: Fruit? = null
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

        window.exitTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.trans_main_content_exit)



        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>
            ) {
                if (mAddedToCart) {
                    var civ = cardHeaderLayout.lastChild()
                    if (mTopCartFull) {
                        civ = civHidden
                    }

                    civ?.apply {
                        sharedElements.clear()
                        sharedElements.put(getString(R.string.transition_fruit), this)
                        mAddedToCart = false
                    }
                }
            }
        })


        cardHeaderLayout.doOnLayout {
            mTopHoldableItemCount = cardHeaderLayout.getHoldableImageCount()
            Logger.d("holdable => ${cardHeaderLayout.getHoldableImageCount()}")
        }

    }

    private fun setupCartAdapter() {
        cartAdapter = CartAdapter(mutableListOf())
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

            addItemDecoration(GridSpaceItemDecoration(24))
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
            putExtra(
                FruitDetailActivity.EXTRA_CART_FULL,
                cardHeaderLayout.isLayoutFull()
            )
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
        mAddedToCart = data?.getBooleanExtra(FruitDetailActivity.EXTRA_ADD_TO_CART, false) ?: false
        mAddedFruit = data?.getParcelableExtra(FruitDetailActivity.EXTRA_FRUIT)

        if (mAddedToCart) {
            startAddingToCartTransition()
        }
    }

    private fun startAddingToCartTransition() {
        postponeEnterTransition()
        if (cardHeaderLayout.isLayoutFull()) {
            mTopCartFull = true
            startPostponedEnterTransition()
        } else {
            val civFruit = RoundedImageView(this)
                .apply {
                    showRoundedBackground = true
                    transitionName =
                        getString(R.string.transition_fruit)
                }

            cardHeaderLayout.addViewWithMarginLeft(civFruit)


            val civ = cardHeaderLayout.lastChild() as RoundedImageView
            civ.load(mAddedFruit?.image ?: 0) {
                Logger.d("height => ${civ.height}")
                startPostponedEnterTransition()
            }
        }

        addFruitTotheCart()
    }

    private fun addFruitTotheCart() {
        val cart = mAddedFruit?.run {
            Cart(image, 1, name, price)
        } ?: Cart()

        cartAdapter.addItem(cart)
    }
}
