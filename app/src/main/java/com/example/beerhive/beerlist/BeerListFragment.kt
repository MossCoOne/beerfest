package com.example.beerhive.beerlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beerhive.R
import com.example.beerhive.beerdetail.BeerDetailActivity
import com.example.beerhive.databinding.FragmentBeerListBinding
import com.example.beerhive.domain.Beer
import dagger.hilt.android.AndroidEntryPoint

private val LOG_TAG: String? = BeerListFragment::class.simpleName

@AndroidEntryPoint
class BeerListFragment : Fragment(R.layout.fragment_beer_list) {

    private val beerViewModel: BeerListViewModel by viewModels()
    private lateinit var binding: FragmentBeerListBinding
    private lateinit var beerListAdapter: BeerListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBeerListBinding.bind(view)

        beerViewModel.beerList.observe(viewLifecycleOwner) {
            displayList(it)
        }
    }

    private fun displayList(it: List<Beer>) {
        dismissProgressDialog()
        Log.d(LOG_TAG, it.toString())
        beerListAdapter = BeerListAdapter(this::navigateToDetailedScreen)
        beerListAdapter.submitList(it)
        binding.listRecyclerView.apply {
            adapter = beerListAdapter
            layoutManager = GridLayoutManager(activity, 3)
            itemAnimator = DefaultItemAnimator()
            visibility = View.VISIBLE
        }
    }

    private fun dismissProgressDialog() {
        binding.progressbar.visibility = View.GONE
    }

    private fun navigateToDetailedScreen(domainBeer: Beer) {
        val intent = Intent(activity, BeerDetailActivity::class.java)
        intent.putExtra(BeerDetailActivity.BEER_EXTRA, domainBeer)
        startActivity(intent)
    }
}