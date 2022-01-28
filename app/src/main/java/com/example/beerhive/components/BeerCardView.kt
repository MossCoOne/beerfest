package com.example.beerhive.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class BeerCardView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
//TODO Remove kotlin synthetics
//    init {
//        val rootView = LayoutInflater.from(context)
//            .inflate(R.layout.beer_card_view_layout, this, true)
//
//        attrs.let {
//
//            val typedArray = context.obtainStyledAttributes(it,
//                    R.styleable.beer_card_view_attributes, 0, 0)
//
//            val beerName = resources.getText(typedArray
//                    .getResourceId(R.styleable
//                            .beer_card_view_attributes_beer_title, R.string.beer_title))
//            val volume = resources.getText(typedArray.getResourceId(R.styleable.beer_card_view_attributes_beer_volume, R.string.volume))
//            val beerIconDrawable = typedArray.getDrawable(R.styleable.beer_card_view_attributes_beer_icon)
//
//            rootView.findViewById<TextView>(R.id.beerNameTextView).text = beerName
//            rootView.findViewById<TextView>(R.id.beerVolumeTextView).text = volume
//            rootView.findViewById<AppCompatImageView>(R.id.beerIconImageView).setImageDrawable(beerIconDrawable)
//
//            typedArray.recycle()
//        }
//    }
//
//    fun setBeerName(beerName: String?) {
//        if (!beerName.isNullOrBlank()) {
//            beerNameTextView.text = beerName
//        } else {
//            beerNameTextView.text = resources.getString(R.string.beer_title)
//        }
//    }
//
//    fun setBeerVolume(beerVolume: String) {
//        if (!beerVolume.isNullOrBlank()) {
//            beerVolumeTextView.text = beerVolume
//        } else {
//            beerVolumeTextView.text = resources.getString(R.string.volume)
//        }
//    }
//
//    fun setBeerImage(beerImageUrl: String?) {
//        if (!beerImageUrl.isNullOrBlank()) {
//            Glide.with(beerIconImageView.context).load(beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
//                    DiskCacheStrategy.RESOURCE)
//                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(beerIconImageView)
//        }
//    }
}