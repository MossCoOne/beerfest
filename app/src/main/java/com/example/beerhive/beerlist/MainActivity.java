package com.example.beerhive.beerlist;

import android.content.Intent;
import android.os.Bundle;

import com.example.beerhive.R;
import com.example.beerhive.beerdetail.Beer;
import com.example.beerhive.beerdetail.BeerDetailActivity;
import com.example.beerhive.beerlist.BeerListAdapter;
import com.example.beerhive.beerlist.BeerRepository;
import com.example.beerhive.beerlist.BeerRepositoryImplentation;
import com.example.beerhive.beerlist.BeerViewModel;
import com.example.beerhive.databinding.ActivityMainBinding;
import com.example.beerhive.network.model.BeerResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BeerListAdapter.BeerItemClickListener {

    private RecyclerView beerListRecyclerView;
    private BeerListAdapter beerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onBeerListScreenCreated();
        beerListRecyclerView =  binding.beerListRecyclerView;

        beerListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerListRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void onBeerListScreenCreated(){
//        BeerViewModel beerViewModel = new  ViewModelProvider(this).get(BeerViewModel.class);
//
//        beerViewModel.getBeerListLiveData().observe(this,beerResponsesList -> {
//            beerListAdapter = new BeerListAdapter(this,beerResponsesList);
//            beerListRecyclerView.setAdapter(beerListAdapter);
//        });

        BeerRepository beerRepository = new BeerRepositoryImplentation();

        beerRepository.loadBeerListFromNetwork(new BeerRepository.BeerLoaderCallback() {
            @Override
            public void onBeerListLoaded(List<BeerResponse> beerResponseList) {

                populateList(beerResponseList);
            }

            @Override
            public void onErrorOccurred() {

            }
        });

    }

    private void populateList(List<BeerResponse> beerResponseList) {
        beerListAdapter = new BeerListAdapter(this,beerResponseList);
        beerListRecyclerView.setAdapter(beerListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBeerItemClicked(BeerResponse beerResponse) {
        navigateToDetailedScreen(beerResponse);
    }

    private void navigateToDetailedScreen(BeerResponse beerResponse) {
        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra("beer_extra", getBeer(beerResponse));
        startActivity(intent);
    }

    private Beer getBeer(BeerResponse beerResponse){
        Beer beer = new Beer();
        beer.setBeerDescription(beerResponse.getDescription());
        beer.setBeerBrewerTips(beerResponse.getBrewersTips());
        beer.setBeerImageUrl(beerResponse.getImageUrl());
        beer.setBeerName(beerResponse.getName());

        return beer;
    }
}
