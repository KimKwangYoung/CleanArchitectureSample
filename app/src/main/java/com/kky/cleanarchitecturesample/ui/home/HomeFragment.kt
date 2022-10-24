package com.kky.cleanarchitecturesample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kky.cleanarchitecturesample.R
import com.kky.cleanarchitecturesample.SampleApplication
import com.kky.cleanarchitecturesample.ui.base.BaseFragment
import com.kky.cleanarchitecturesample.databinding.FragmentHomeBinding
import com.kky.cleanarchitecturesample.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModels()

    private val keywordAdapter = KeywordRankListAdapter()

    override fun bind(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.recyclerView.adapter = keywordAdapter

        observe()
    }

    private fun observe() {
        viewModel.state.onEach {
            if (it.loadState == LoadState.SUCCESS) {
                keywordAdapter.submitList(it.histories)
            }
        }.launchIn(lifecycleScope)

        viewModel.event.onEach {
            when(it) {
                HomeViewModel.HomeEvent.NAVIGATE_SEARCH -> navigateToSearch()
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToSearch() {
        findNavController().navigate(R.id.action_home_fragment_to_search_fragment)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}