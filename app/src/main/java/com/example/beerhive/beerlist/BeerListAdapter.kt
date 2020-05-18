package com.example.beerhive.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beerhive.R
import com.example.beerhive.beerlist.BeerListAdapter.BeerListViewHolder
import com.example.beerhive.databinding.BeerItemLayoutBinding
import com.example.beerhive.domain.Beer

class BeerListAdapter(private val beerItemClickListener: BeerItemClickListener,
                      private val beersList: List<Beer>) : RecyclerView.Adapter<BeerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val binding: BeerItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.beer_item_layout, parent, false)
        return BeerListViewHolder(binding, beerItemClickListener)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beerResponse = beersList[position]
        holder.bindData(beerResponse)
        holder.setBeerResponse(beerResponse)
    }

    override fun getItemCount(): Int {
        return beersList.size
    }

    interface BeerItemClickListener {
        fun onBeerItemClicked(domainBeer: Beer)
    }

    class BeerListViewHolder(private var binding: BeerItemLayoutBinding,
                             private val beerItemClickListener: BeerItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var domainBeer: Beer
        fun setBeerResponse(domainBeer: Beer) {
            this.domainBeer = domainBeer
        }

        fun bindData(domainBeer: Beer) {
            binding.beerCardView.setBeerImage(domainBeer.beerImageUrl)
            binding.beerCardView.setBeerName(domainBeer.beerName)
            binding.beerCardView.setBeerVolume("${domainBeer.volume} ${domainBeer.volumeUnit}")
        }

        init {
            binding.beerCardView.setOnClickListener { beerItemClickListener.onBeerItemClicked(domainBeer) }
        }
    }

}