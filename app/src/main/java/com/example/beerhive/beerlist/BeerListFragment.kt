package com.example.beerhive.beerlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beerhive.R
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
        beerListAdapter = BeerListAdapter(this::navigateToDetailedScreen)
        binding.listRecyclerView.apply {
            adapter = beerListAdapter
            layoutManager = GridLayoutManager(activity, 3)
            itemAnimator = DefaultItemAnimator()
        }

        beerViewModel.beerList.observe(viewLifecycleOwner) {
            displayList(it)
        }
    }

    private fun displayList(it: List<Beer>) {
        dismissProgressDialog()
        beerListAdapter.submitList(it)
        binding.listRecyclerView.visibility = View.VISIBLE
        Log.d(LOG_TAG, it.toString())
    }

    private fun dismissProgressDialog() {
        binding.progressbar.visibility = View.GONE
    }

    private fun navigateToDetailedScreen(domainBeer: Beer) {
        val navToDetailScreenAction =
            BeerListFragmentDirections.actionBeerListFragmentToBeerDetailsFragment(domainBeer)
        findNavController().navigate(navToDetailScreenAction)
    }
}