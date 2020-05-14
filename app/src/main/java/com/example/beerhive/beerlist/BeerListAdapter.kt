package com.example.beerhive.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beerhive.R
import com.example.beerhive.beerlist.BeerListAdapter.BeerListViewHolder
import com.example.beerhive.databinding.BeerItemLayoutBinding
import com.example.beerhive.domain.Beer
import kotlinx.android.synthetic.main.beer_item_layout.view.*

class BeerListAdapter(private val beerItemClickListener: BeerItemClickListener,
                      private val beersList: List<Beer>) : RecyclerView.Adapter<BeerListViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val domainBeer = beersList[position]
        holder.bindData(domainBeer)
        holder.itemView.beerCardView.setOnClickListener { beerItemClickListener.onBeerItemClicked(domainBeer) }
    }

    override fun getItemCount(): Int {
        return beersList.size
    }

    interface BeerItemClickListener {
        fun onBeerItemClicked(domainBeer: Beer)

    }

    class BeerListViewHolder private constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var domainBeer: Beer

        fun bindData(domainBeer: Beer) {
            this.domainBeer = domainBeer
            view.beerCardView.setBeerImage(domainBeer.beerImageUrl)
            view.beerCardView.beerCardView.setBeerName(domainBeer.beerName)
            view.beerCardView.setBeerVolume("${domainBeer.volume} ${domainBeer.volumeUnit}")
        }

        companion object {
            fun fromParent(parent: ViewGroup): BeerListViewHolder {
                val binding: BeerItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.beer_item_layout, parent, false)
                return BeerListViewHolder(binding.root)
            }
        }
    }

}