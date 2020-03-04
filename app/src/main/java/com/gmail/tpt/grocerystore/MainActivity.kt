package com.gmail.tpt.grocerystore

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.AttributeSet
import android.view.Menu
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.doOnLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

/*    private fun prePopulateFruiteItem() {
        val civFruit = CircularImageView(this)
            .apply {
                transitionName =
                    getString(R.string.transition_fruit)
            }

        cardHeaderLayout.addViewWithMarginLeft(civFruit)
    }*/


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
//        prePopulateFruiteItem()


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

        mAddedToCart = data?.getBooleanExtra(FruitDetailActivity.EXTRA_ADD_TO_CART, false) ?: false
        mAddedFruit = data?.getParcelableExtra(FruitDetailActivity.EXTRA_FRUIT)

        if (mAddedToCart) {
            startAddingToCartTransition()
        }
    }

    private fun startAddingToCartTransition() {
        postponeEnterTransition()
        if (cardHeaderLayout.childCount == mTopHoldableItemCount) {
            mTopCartFull = true
            startPostponedEnterTransition()
        } else {
            val civFruit = CircularImageView(this)
                .apply {
                    borderWidth = 1f
                    transitionName =
                        getString(R.string.transition_fruit)
                }

            cardHeaderLayout.addViewWithMarginLeft(civFruit)


            val civ = cardHeaderLayout.lastChild() as CircularImageView
            civ.load(mAddedFruit?.image ?: 0) {
                startPostponedEnterTransition()
            }
        }
    }
}
