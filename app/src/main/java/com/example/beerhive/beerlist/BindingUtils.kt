package com.example.beerhive.beerlist

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.beerhive.R
import com.example.beerhive.domain.Beer

@BindingAdapter("beerName")
fun TextView.setBeerName(beer:Beer){
    beer.let {
        text = beer.beerName
    }
}

@BindingAdapter("beerVolume")
fun TextView.setBeerVolume(beer: Beer){
    beer.let {
        text = "${beer.volume} ${beer.volumeUnit}"
    }
}

@BindingAdapter("beerImage")
fun AppCompatImageView.setBeerImage(beer: Beer){
    beer.let {
        Glide.with(context).load(beer.beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(this)
    }
}