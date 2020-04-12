package com.example.beerhive.beerlist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerhive.R;
import com.example.beerhive.beerdetail.Beer;
import com.example.beerhive.beerdetail.BeerDetailActivity;
import com.example.beerhive.databinding.ActivityMainBinding;
import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BeerListAdapter.BeerItemClickListener, BeerContract.BeerView {

    private RecyclerView beerListRecyclerView;
    private BeerListAdapter beerListAdapter;
    private ProgressDialog progressDialog;

    private BeerPresenter newsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.beer_list_title));
        progressDialog = new ProgressDialog(this);

        onBeerListScreenCreated();
        beerListRecyclerView = binding.beerListRecyclerView;

        beerListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerListRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void onBeerListScreenCreated() {
        newsPresenter = new BeerPresenter(this);
        newsPresenter.loadBeerList();

    }

    @Override
    public void onBeerItemClicked(BeerResponse beerResponse) {
        navigateToDetailedScreen(beerResponse);
    }

    private void navigateToDetailedScreen(BeerResponse beerResponse) {
        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra(BeerDetailActivity.BEER_EXTRA, getBeer(beerResponse));
        startActivity(intent);
    }

    private Beer getBeer(BeerResponse beerResponse) {
        Beer beer = new Beer();
        beer.setBeerDescription(beerResponse.getDescription());
        beer.setBeerBrewerTips(beerResponse.getBrewersTips());
        beer.setBeerImageUrl(beerResponse.getImageUrl());
        beer.setBeerName(beerResponse.getName());

        return beer;
    }

    @Override
    public void displayBeerList(List<BeerResponse> responseList) {
        beerListAdapter = new BeerListAdapter(this, responseList);
        beerListRecyclerView.setAdapter(beerListAdapter);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.setTitle(getString(R.string.places_loading));
            progressDialog.show();
            progressDialog.setMessage(getString(R.string.please_wait_message));
            progressDialog.setIndeterminate(true);
        }
    }

    @Override
    public void showErrorMesage() {
        progressDialog.dismiss();
        showCustomDialog("Something went wrong");
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    public void showCustomDialog(String titleText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleText);
        builder.setIcon(R.drawable.ic_error_outline_black_24dp);
        builder.setNegativeButton(R.string.cancel_text, (dialog, which) -> finish());
        builder.show().setCancelable(false);
    }
}
