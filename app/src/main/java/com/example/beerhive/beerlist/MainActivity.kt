package com.example.beerhive.beerlist

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beerhive.R
import com.example.beerhive.beerdetail.BeerDetailActivity
import com.example.beerhive.beerlist.BeerListAdapter.BeerItemClickListener
import com.example.beerhive.database.BeerDatabase
import com.example.beerhive.databinding.ActivityMainBinding
import com.example.beerhive.domain.Beer

class MainActivity : AppCompatActivity(), BeerItemClickListener {
    companion object {
        private val LOG_TAG: String? = MainActivity::class.simpleName
    }

    private lateinit var beerViewModel: BeerViewModel
    private var beerListRecyclerView: RecyclerView? = null
    private var beerListAdapter: BeerListAdapter? = null
    private var progressDialog: ProgressDialog? = null
    private lateinit var databaseDao: BeerDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        showProgressDialog()
        databaseDao = BeerDatabase.getInstance(application)

        val application = requireNotNull(this).application
        val viewModelFactory = BeerViewModelFactory(databaseDao, application)
        beerViewModel = ViewModelProvider(this, viewModelFactory).get(BeerViewModel::class.java)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.beer_list_title)
        progressDialog = ProgressDialog(this)

        beerListRecyclerView = binding.beerListRecyclerView
        beerListRecyclerView?.layoutManager = GridLayoutManager(this, 3)
        beerListRecyclerView?.itemAnimator = DefaultItemAnimator()
        beerViewModel.beerList.observe(this, Observer { onListLoaded(it) })
    }

    private fun onListLoaded(it: List<Beer>) {
        dismissProgressDialog()
        Log.d(LOG_TAG, it.toString())
        beerListAdapter = BeerListAdapter(this, it)
        beerListRecyclerView?.adapter = beerListAdapter
    }

    override fun onBeerItemClicked(domainBeer: Beer) {
        navigateToDetailedScreen(domainBeer)
    }

    private fun navigateToDetailedScreen(domainBeer: Beer) {
        val intent = Intent(this, BeerDetailActivity::class.java)
        intent.putExtra(BeerDetailActivity.BEER_EXTRA, domainBeer)
        startActivity(intent)
    }

    private fun showProgressDialog() {
        if (progressDialog != null) {
            progressDialog?.setTitle(getString(R.string.places_loading))
            progressDialog?.show()
            progressDialog?.setMessage(getString(R.string.please_wait_message))
            progressDialog?.isIndeterminate = true
        }
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    private fun showCustomDialog(titleText: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleText)
        builder.setIcon(R.drawable.ic_error_outline_black_24dp)
        builder.setNegativeButton(R.string.cancel_text) { dialog: DialogInterface?, which: Int -> finish() }
        builder.show().setCancelable(false)
    }
}