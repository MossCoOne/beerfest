package com.example.beerhive.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.beerhive.R
import com.example.beerhive.beerlist.BeerListAdapter.BeerListViewHolder
import com.example.beerhive.databinding.BeerItemLayoutBinding
import com.example.beerhive.domain.Beer

class BeerListAdapter(private val navigateToBeerDetailScreen: (domainBeer: Beer) -> Unit) :
        ListAdapter<Beer, BeerListViewHolder>(BeerListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val domainBeer = getItem(position)
        holder.bindData(domainBeer, navigateToBeerDetailScreen)
    }

    class BeerListViewHolder private constructor(private val binding: BeerItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(domainBeer: Beer, navigateToBeerDetailScreen: (domainBeer: Beer) -> Unit) {

            Glide.with(binding.beerImageView.context).load(domainBeer.beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView)

            binding.beerNameTextView.text = domainBeer.beerName
            binding.valueOfBeerTextView.text = "${domainBeer.volume} ${domainBeer.volumeUnit}"

            binding.beerCardView.setOnClickListener {
                navigateToBeerDetailScreen(domainBeer)
            }
        }

        companion object {
            fun fromParent(parent: ViewGroup): BeerListViewHolder {
                val binding = BeerItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
                        parent, false)
                return BeerListViewHolder(binding)
            }
        }
    }

}

class BeerListDiffCallBack : DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.beerImageUrl == newItem.beerImageUrl
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }

}