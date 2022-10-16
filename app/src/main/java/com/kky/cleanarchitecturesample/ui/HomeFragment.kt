package com.kky.cleanarchitecturesample.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kky.cleanarchitecturesample.R
import com.kky.cleanarchitecturesample.base.BaseFragment
import com.kky.cleanarchitecturesample.databinding.FragmentHomeBinding
import com.kky.cleanarchitecturesample.ui.state.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun bind(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        observe()
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is State.Success -> {}
                is State.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                State.None -> {/* blank */}
            }
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when(it) {
                HomeViewModel.HomeEvent.NAVIGATE_SEARCH -> navigateToSearch()
            }
        }
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