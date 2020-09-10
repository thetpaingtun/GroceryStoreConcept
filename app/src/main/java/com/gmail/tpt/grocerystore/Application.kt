package com.gmail.tpt.grocerystore

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import jp.wasabeef.takt.Seat
import jp.wasabeef.takt.Takt

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        Takt.stock(this)
            .seat(Seat.BOTTOM_RIGHT)


    }
}