package com.ajbell.technicaltest.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ajbell.technicaltest.databinding.FragmentMarketListBinding
import com.ajbell.technicaltest.list.MarketListEvent.ShowMarketDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MarketListFragment : Fragment() {

    private val viewModel by viewModels<MarketListViewModel>()
    private var binding: FragmentMarketListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMarketListBinding.inflate(inflater, container, false).let {
        binding = it
        initBinding()
        it.root
    }

    private fun initBinding() {
        binding?.recyclerView?.adapter = MarketListRecyclerViewAdapter(viewModel::onItemClick)

        viewModel.markets.observe(viewLifecycleOwner) { markets ->
            (binding?.recyclerView?.adapter as? MarketListRecyclerViewAdapter)?.data = markets
        }

        viewModel.event.observe(viewLifecycleOwner) { event ->
            when(event) {
                is ShowMarketDetail -> showMarketDetail(event)
            }
        }
    }

    private fun showMarketDetail(event: ShowMarketDetail) {
        val navAction = MarketListFragmentDirections.actionMarketListFragmentToMarketDetailFragment(event)
        findNavController().navigate(navAction)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}