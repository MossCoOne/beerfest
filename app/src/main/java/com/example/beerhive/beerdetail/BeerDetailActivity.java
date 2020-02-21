package com.example.beerhive.beerdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.beerhive.R;
import com.example.beerhive.databinding.ActivityBeerDetailBinding;
import com.example.beerhive.databinding.ActivityMainBinding;

public class BeerDetailActivity extends AppCompatActivity {

    private final String beer_extra = "beer_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBeerDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail);

        if (getIntent() != null && getIntent().hasExtra(beer_extra)){
            Beer beer =  getIntent().getParcelableExtra(beer_extra);

            binding.beerDescriptionTextView.setText(beer.getBeerDescription());
            binding.beerNameTextView.setText(beer.getBeerName());
            binding.brewerTipsTextView.setText(beer.getBeerBrewerTips());

            Glide.with(binding.beerImageView.getContext()).load(beer.getBeerImageUrl()).dontAnimate().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.beerImageView);
        }
    }
}
