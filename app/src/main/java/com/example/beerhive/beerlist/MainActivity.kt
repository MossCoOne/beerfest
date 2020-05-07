package com.example.beerhive.beerlist

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beerhive.R
import com.example.beerhive.beerdetail.Beer
import com.example.beerhive.beerdetail.BeerDetailActivity
import com.example.beerhive.beerlist.BeerContract.BeerView
import com.example.beerhive.beerlist.BeerListAdapter.BeerItemClickListener
import com.example.beerhive.databinding.ActivityMainBinding
import com.example.beerhive.network.model.BeerResponse

class MainActivity : AppCompatActivity(), BeerItemClickListener, BeerView {
    private lateinit var beerViewModel: BeerViewModel
    private var beerListRecyclerView: RecyclerView? = null
    private var beerListAdapter: BeerListAdapter? = null
    private var progressDialog: ProgressDialog? = null
    private var newsPresenter: BeerPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       // beerViewModel = ViewModelProvider(this).get(BeerViewModel::class.java)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.beer_list_title)
        progressDialog = ProgressDialog(this)
        onBeerListScreenCreated()
        beerListRecyclerView = binding.beerListRecyclerView
        beerListRecyclerView?.layoutManager = LinearLayoutManager(this)
        beerListRecyclerView?.itemAnimator = DefaultItemAnimator()
    }

    private fun onBeerListScreenCreated() {
        newsPresenter = BeerPresenter(this)
        newsPresenter?.loadBeerList()
    }

    override fun onBeerItemClicked(beerResponse: BeerResponse) {
        navigateToDetailedScreen(beerResponse)
    }

    private fun navigateToDetailedScreen(beerResponse: BeerResponse) {
        val intent = Intent(this, BeerDetailActivity::class.java)
        intent.putExtra(BeerDetailActivity.BEER_EXTRA, getBeer(beerResponse))
        startActivity(intent)
    }

    private fun getBeer(beerResponse: BeerResponse): Beer {
        val beer = Beer()
        beer.beerDescription = beerResponse.description
        beer.beerBrewerTips = beerResponse.brewersTips
        beer.beerImageUrl = beerResponse.imageUrl
        beer.beerName = beerResponse.name
        return beer
    }

    override fun displayBeerList(responseList:List<BeerResponse?>?) {
        beerListAdapter = BeerListAdapter(this, responseList)
        beerListRecyclerView?.adapter = beerListAdapter
    }

    override fun showProgressDialog() {
        if (progressDialog != null) {
            progressDialog?.setTitle(getString(R.string.places_loading))
            progressDialog?.show()
            progressDialog?.setMessage(getString(R.string.please_wait_message))
            progressDialog?.isIndeterminate = true
        }
    }

    override fun showErrorMessage() {
        progressDialog!!.dismiss()
        showCustomDialog(getString(R.string.something_went_wrong_error_message))
    }

    override fun dismissProgressDialog() {
        progressDialog!!.dismiss()
    }

    private fun showCustomDialog(titleText: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleText)
        builder.setIcon(R.drawable.ic_error_outline_black_24dp)
        builder.setNegativeButton(R.string.cancel_text) { dialog: DialogInterface?, which: Int -> finish() }
        builder.show().setCancelable(false)
    }
}