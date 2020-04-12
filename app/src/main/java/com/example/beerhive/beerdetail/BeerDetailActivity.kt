package com.example.beerhive.beerdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.beerhive.R
import com.example.beerhive.databinding.ActivityBeerDetailBinding

class BeerDetailActivity : AppCompatActivity() {

    companion object{
        const val BEER_EXTRA = "beer_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBeerDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail)
        if (intent != null && intent.hasExtra(BEER_EXTRA)) {
            val beer: Beer = intent.getParcelableExtra(BEER_EXTRA)
            binding.beerDescriptionTextView.text = beer.beerDescription
            binding.beerNameTextView.text = beer.beerName
            binding.brewerTipsTextView.text = beer.beerBrewerTips
            Glide.with(binding.beerImageView.context).load(beer.beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView)
        }
    }
}