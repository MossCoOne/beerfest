package com.example.beerhive;

import android.os.Bundle;

import com.example.beerhive.beerlist.BeerRepository;
import com.example.beerhive.beerlist.BeerRepositoryImplentation;
import com.example.beerhive.beerlist.BeerViewModel;
import com.example.beerhive.network.model.BeerResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        BeerRepository beerRepository =  new BeerRepositoryImplentation();
        beerRepository.loadBeerListFromNetwork(new BeerRepository.BeerLoaderCallback() {
            @Override
            public void onBeerListLoaded(List<BeerResponse> newsArticle) {
                Log.d("Yay Success",newsArticle.toString());
            }

            @Override
            public void onErrorOccurred() {
                Log.d("Nay Failure","So sadddd");
            }
        });

        onBeerListScreenCreated();
    }

    private void onBeerListScreenCreated(){
        BeerViewModel beerViewModel = new  ViewModelProvider(this).get(BeerViewModel.class);

        beerViewModel.getBeerListLiveData().observe(this,beerResponsesList -> {

        });

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
}
