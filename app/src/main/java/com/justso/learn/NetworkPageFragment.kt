package com.justso.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.LoadState
import androidx.paging.PagedList
import com.justso.learn.adapter.NetworkPageAdapter
import com.justso.learn.adapter.load.PageLoadStateAdapter
import com.justso.learn.databinding.FragmentNetworkPageBinding
import com.justso.learn.page.fake.CustomPageDataSourceFactory
import com.justso.learn.page.fake.DataRepository
import com.justso.learn.page.fake.PageAdapter
import com.justso.learn.utils.InjectorUtils
import com.justso.learn.viewmodels.NetworkViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NetworkPageFragment: Fragment() {
    private val networkViewModel:NetworkViewModel by viewModels {
       InjectorUtils.providerNetworkViewModelFactory(this)
    }
    private lateinit var mAdapter:NetworkPageAdapter
    private lateinit var pageAdapter:PageAdapter
    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNetworkPageBinding.inflate(inflater)
        initAdapter(binding)
        initSwipeRefresh(binding)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    private fun initAdapter(binding:FragmentNetworkPageBinding) {
        mAdapter = NetworkPageAdapter()
        binding.rvList.adapter = mAdapter
        binding.rvList.adapter = mAdapter.withLoadStateHeaderAndFooter(header = PageLoadStateAdapter(mAdapter),footer = PageLoadStateAdapter(mAdapter))
//        pageAdapter = PageAdapter()
//        binding.rvList.adapter = pageAdapter

        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading

            }
        }
        lifecycleScope.launch {
            networkViewModel.data.collectLatest {
                mAdapter.submitData(it)
            }
        }
        //fakeData()
    }

    private fun initSwipeRefresh(binding:FragmentNetworkPageBinding) {
        binding.swipeRefresh.setOnRefreshListener {
            mAdapter.refresh()
//            fakeData()
//            binding.swipeRefresh.isRefreshing = false
        }
    }
    private fun fakeData(){
        val data = LivePagedListBuilder(CustomPageDataSourceFactory(repository = DataRepository()),PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .build()
        ).build()
        data.observe(this.viewLifecycleOwner, Observer {
            pageAdapter.submitList(it)
        })

    }
}