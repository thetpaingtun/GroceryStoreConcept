package com.gmail.tpt.grocerystore.tes

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.Window
import com.gmail.tpt.grocerystore.Logger
import com.gmail.tpt.grocerystore.R
import com.gmail.tpt.grocerystore.load
import kotlinx.android.synthetic.main.activity_tes.*
import kotlinx.android.synthetic.main.activity_test_detail.*

class TestDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)

        postponeEnterTransition()



        imgDetail.load(R.drawable.strawberry) {
            startPostponedEnterTransition()
        }

        imgDetail.setOnClickListener {
            finishAfterTransition()
        }

        btnCart.setOnClickListener {
            //            window.sharedElementReturnTransition = Fade()
//            window.sharedElementExitTransition = Fade()
//            imgDetail.transitionName = null


            setResult(Activity.RESULT_OK, Intent().apply { putExtra("EXTRA_CART", true) })
            finishAfterTransition()

        }

/*        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>
            ) {
                imgDetail.transitionName = "img_new"
                sharedElements.clear()
                sharedElements.put("img_new", imgDetail)
                Logger.d("enter map => " + sharedElements)

            }
        })*/
    }


}
