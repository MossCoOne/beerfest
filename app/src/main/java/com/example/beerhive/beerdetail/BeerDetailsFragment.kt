package com.example.beerhive.beerdetail


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.beerhive.R
import com.example.beerhive.databinding.FragmentBeerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : Fragment(R.layout.fragment_beer_details) {

    lateinit var binding: FragmentBeerDetailsBinding
    private val args : BeerDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBeerDetailsBinding.bind(view)
        args?.beerDetail.let {
            binding.beerDetail = it
            Glide.with(binding.beerImageView.context).load(it?.beerImageUrl).dontAnimate().fitCenter().diskCacheStrategy(
                DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView)
        }
    }

}