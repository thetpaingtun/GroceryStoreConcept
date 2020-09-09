package com.gmail.tpt.grocerystore.tes

import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.gmail.tpt.grocerystore.Logger
import com.gmail.tpt.grocerystore.R
import kotlinx.android.synthetic.main.activity_tes.*

class TesActivity : AppCompatActivity() {

    private var mCart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)




        imgFruit.setOnClickListener {
            val intent = Intent(this, TestDetail::class.java)

            val option =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgFruit, "img_trans")

            startActivityForResult(intent, 12, option.toBundle())

        }


        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>
            ) {
                if (mCart) {
                    sharedElements.clear()
                    sharedElements.put("img_trans", imgCart)
                    mCart = false
                }

                Logger.d("exit on map => " + sharedElements)
            }

        })

    }

    override fun onActivityReenter(resultCode: Int, data: Intent) {
        super.onActivityReenter(resultCode, data)
        mCart = data.getBooleanExtra("EXTRA_CART", false)
        Logger.d("reenter => ")
    }
}
