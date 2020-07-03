package com.justso.learn.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.justso.learn.databinding.FragmentPageBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

class PageFragment:Fragment() {
    private lateinit var mAdapter:LatestPageAdapter
    private val viewModel:PageViewModel by viewModels {
        Injector.providePageViewModelFactory(requireContext())
    }
    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPageBinding.inflate(inflater,container,false)
        initAdapter(binding)
        initSwipeRefresh(binding)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    private fun initSwipeRefresh(binding: FragmentPageBinding) {
        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
        binding.swipeRefresh.setOnRefreshListener { mAdapter.refresh() }
    }

    @ExperimentalCoroutinesApi
    private fun initAdapter(binding: FragmentPageBinding) {
        mAdapter = LatestPageAdapter()
        binding.rvList.adapter = mAdapter
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                mAdapter.submitData(it)
            }
        }

    }
}