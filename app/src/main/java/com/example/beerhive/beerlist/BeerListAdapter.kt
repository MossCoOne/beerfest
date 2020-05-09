package com.example.beerhive.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.beerhive.R
import com.example.beerhive.beerlist.BeerListAdapter.BeerListViewHolder
import com.example.beerhive.databinding.BeerItemLayoutBinding
import com.example.beerhive.domain.Beer

class BeerListAdapter(private val beerItemClickListener: BeerItemClickListener, private val beersList: List<Beer>) : RecyclerView.Adapter<BeerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val binding: BeerItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.beer_item_layout, parent, false)
        return BeerListViewHolder(binding, beerItemClickListener)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beerResponse = beersList[position]
        holder.bindData(beerResponse)
        holder.setBeerResponse(beerResponse)
    }

    override fun getItemCount(): Int {
        return beersList?.size ?: 0
    }

    interface BeerItemClickListener {
        fun onBeerItemClicked(domainBeer: Beer)
    }

    class BeerListViewHolder(var binding: BeerItemLayoutBinding, private val beerItemClickListener: BeerItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        lateinit var domainBeer: Beer
        fun setBeerResponse(domainBeer: Beer) {
            this.domainBeer = domainBeer
        }

        fun bindData(domainBeer: Beer) {
            binding.beerNameTextView.text = domainBeer.beerName
            binding.valueOfBeerTextView.text = "${domainBeer.volume} ${domainBeer.volumeUnit}"
            Glide.with(binding.beerImageView.context).load(domainBeer.beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView)
        }

        init {
            binding.beerItemContainer.setOnClickListener { v: View? -> beerItemClickListener.onBeerItemClicked(domainBeer) }
        }
    }

}