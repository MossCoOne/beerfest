package com.example.beerhive.beerlist

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beerhive.R
import com.example.beerhive.beerdetail.BeerDetailActivity
import com.example.beerhive.database.BeerDatabase
import com.example.beerhive.databinding.ActivityMainBinding
import com.example.beerhive.domain.Beer

class MainActivity : AppCompatActivity() {
    companion object {
        private val LOG_TAG: String? = MainActivity::class.simpleName
    }

    private lateinit var beerViewModel: BeerViewModel
    private var beerListAdapter: BeerListAdapter? = null
    private lateinit var databaseDao: BeerDatabase
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.progressbar.visibility = View.VISIBLE

        databaseDao = BeerDatabase.getInstance(application)
        val application = requireNotNull(this).application
        val viewModelFactory = BeerViewModelFactory(databaseDao, application)
        beerViewModel = ViewModelProvider(this, viewModelFactory).get(BeerViewModel::class.java)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.beer_list_title)

        beerViewModel.beerList.observe(this, Observer { onListLoaded(it) })
    }

    private fun onListLoaded(it: List<Beer>) {
        dismissProgressDialog()
        Log.d(LOG_TAG, it.toString())
        beerListAdapter = BeerListAdapter(this::navigateToDetailedScreen)
        beerListAdapter?.beersList = it
        val beerListRecyclerView = binding.beerListRecyclerView
        beerListRecyclerView.layoutManager = GridLayoutManager(this, 3)
        beerListRecyclerView.itemAnimator = DefaultItemAnimator()
        beerListRecyclerView.adapter = beerListAdapter
        beerListRecyclerView.visibility = View.VISIBLE

    }

    private fun navigateToDetailedScreen(domainBeer: Beer) {
        val intent = Intent(this, BeerDetailActivity::class.java)
        intent.putExtra(BeerDetailActivity.BEER_EXTRA, domainBeer)
        startActivity(intent)
    }

    private fun dismissProgressDialog() {
        binding.progressbar.visibility = View.GONE
    }

    private fun showCustomDialog(titleText: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleText)
        builder.setIcon(R.drawable.ic_error_outline_black_24dp)
        builder.setNegativeButton(R.string.cancel_text) { _: DialogInterface?, _: Int -> finish() }
        builder.show().setCancelable(false)
    }
}