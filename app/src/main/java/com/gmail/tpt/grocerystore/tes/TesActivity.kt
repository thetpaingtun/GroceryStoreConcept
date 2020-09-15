package com.gmail.tpt.grocerystore.tes

import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.gmail.tpt.grocerystore.Logger
import com.gmail.tpt.grocerystore.R
import com.gmail.tpt.grocerystore.dp
import kotlinx.android.synthetic.main.activity_tes.*

class TesActivity : AppCompatActivity() {

    private var mCart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.rect)

/*        val drawable = RoundedBitmapDrawableFactory.create(
            resources,
            bitmap
        ).apply {
            cornerRadius = dp(24f)
            setAntiAlias(true)
        }*/

//        imgRound.setImageResource(R.drawable.apple)

//        clip.setBackgroundColor(Color.GREEN)
//        clip.setBackgroundResource(R.drawable.pattern2)


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
