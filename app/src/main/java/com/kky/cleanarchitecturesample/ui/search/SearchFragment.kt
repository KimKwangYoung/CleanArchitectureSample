package com.kky.cleanarchitecturesample.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.kky.cleanarchitecturesample.R
import com.kky.cleanarchitecturesample.ui.base.BaseFragment
import com.kky.cleanarchitecturesample.databinding.FragmentSearchBinding
import com.kky.cleanarchitecturesample.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private val viewModel: SearchViewModel by viewModels()

    private val postListAdapter = PostListAdapter()

    override fun bind(): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.recyclerView.adapter = postListAdapter

        observe()

        val args by navArgs<SearchFragmentArgs>()
        args.keyword?.let {
            binding.etKeyword.setText(it)
            viewModel.search(it)
        }
    }

    private fun observe() {
        viewModel.state.onEach {
            if (it.loadState == LoadState.SUCCESS) {
                postListAdapter.submitList(it.posts)
            }
        }.launchIn(lifecycleScope)

        viewModel.event.onEach {
            when(it) {
                SearchViewModel.SearchEvent.EMPTY_KEYWORD -> Toast.makeText(requireContext(), R.string.input_keyword, Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }
}