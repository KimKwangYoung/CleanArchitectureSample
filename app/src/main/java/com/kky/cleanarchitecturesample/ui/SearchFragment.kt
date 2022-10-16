package com.kky.cleanarchitecturesample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kky.cleanarchitecturesample.R
import com.kky.cleanarchitecturesample.base.BaseFragment
import com.kky.cleanarchitecturesample.databinding.FragmentSearchBinding
import com.kky.cleanarchitecturesample.ui.state.State
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is State.Success -> postListAdapter.submitList(it.data)
                is State.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                is State.None -> Log.d("SearchFragment","none")
            }
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when(it) {
                SearchViewModel.SearchEvent.EMPTY_KEYWORD -> Toast.makeText(requireContext(), R.string.input_keyword, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }
}