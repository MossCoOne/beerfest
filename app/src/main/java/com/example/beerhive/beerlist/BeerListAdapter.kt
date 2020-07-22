package com.example.beerhive.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beerhive.R
import com.example.beerhive.beerlist.BeerListAdapter.BeerListViewHolder
import com.example.beerhive.databinding.BeerItemLayoutBinding
import com.example.beerhive.domain.Beer
import kotlinx.android.synthetic.main.beer_item_layout.view.*

class BeerListAdapter(private val navigateToBeerDetailScreen: (domainBeer: Beer) -> Unit) :
        ListAdapter<Beer, BeerListViewHolder>(BeerListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val domainBeer = getItem(position)
        holder.bindData(domainBeer, navigateToBeerDetailScreen)
    }

    class BeerListViewHolder private constructor(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(domainBeer: Beer, navigateToBeerDetailScreen: (domainBeer: Beer) -> Unit) {
            view.beerCardView.setBeerImage(domainBeer.beerImageUrl)
            view.beerCardView.beerCardView.setBeerName(domainBeer.beerName)
            view.beerCardView.setBeerVolume("${domainBeer.volume} ${domainBeer.volumeUnit}")

            view.beerCardView.setOnClickListener {
                navigateToBeerDetailScreen(domainBeer)
            }
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

class BeerListDiffCallBack : DiffUtil.ItemCallback<Beer>(){
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
       return oldItem.beerImageUrl == newItem.beerImageUrl
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }

}