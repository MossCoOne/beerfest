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
import com.example.beerhive.network.model.BeerResponse

class BeerListAdapter(private val beerItemClickListener: BeerItemClickListener, private val beerResponseList: List<BeerResponse?>?) : RecyclerView.Adapter<BeerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val binding: BeerItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.beer_item_layout, parent, false)
        return BeerListViewHolder(binding, beerItemClickListener)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beerResponse = beerResponseList!![position]
        holder.bindData(beerResponse)
        holder.setBeerResponse(beerResponse)
    }

    override fun getItemCount(): Int {
        return beerResponseList?.size ?: 0
    }

    interface BeerItemClickListener {
        fun onBeerItemClicked(beerResponse: BeerResponse?)
    }

    class BeerListViewHolder(var binding: BeerItemLayoutBinding, private val beerItemClickListener: BeerItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        private var beerResponse: BeerResponse? = null
        fun setBeerResponse(beerResponse: BeerResponse?) {
            this.beerResponse = beerResponse
        }

        fun bindData(beerResponse: BeerResponse?) {
            binding.beerNameTextView.text = beerResponse?.name
            binding.valueOfBeerTextView.text = "${beerResponse?.volume?.value.toString()} ${beerResponse?.volume?.unit}"
            Glide.with(binding.beerImageView.context).load(beerResponse?.imageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView)
        }

        init {
            binding.beerItemContainer.setOnClickListener { v: View? -> beerItemClickListener.onBeerItemClicked(beerResponse) }
        }
    }

}