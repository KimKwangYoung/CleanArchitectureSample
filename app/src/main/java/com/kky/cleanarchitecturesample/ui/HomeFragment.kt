package com.kky.cleanarchitecturesample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kky.cleanarchitecturesample.R
import com.kky.cleanarchitecturesample.base.BaseFragment
import com.kky.cleanarchitecturesample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModels()

    private val postListAdapter = PostListAdapter()

    override fun bind(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = postListAdapter

        observe()
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is HomeViewModel.State.Success -> postListAdapter.submitList(it.data)
                is HomeViewModel.State.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                HomeViewModel.State.None -> {/* blank */}
            }
        }
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