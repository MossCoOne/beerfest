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

class BeerListAdapter(private val navigateToBeerDetailScreen: (domainBeer: Beer) -> Unit) :
        RecyclerView.Adapter<BeerListViewHolder>() {

    var beersList = listOf<Beer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val domainBeer = beersList[position]
        holder.bindData(domainBeer, navigateToBeerDetailScreen)
    }

    override fun getItemCount(): Int {
        return beersList.size
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