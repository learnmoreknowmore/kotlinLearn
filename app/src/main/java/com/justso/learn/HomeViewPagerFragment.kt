package com.justso.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.justso.learn.adapter.MY_GARDEN_PAGE_INDEX
import com.justso.learn.adapter.NETWORK_PAGE_INDEX
import com.justso.learn.adapter.PLANT_LIST_PAGE_INDEX
import com.justso.learn.adapter.SunflowerPagerAdapter
import com.justso.learn.databinding.FragmentViewPagerBinding

class HomeViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = com.justso.learn.databinding.FragmentViewPagerBinding.inflate(
            inflater,
            container,
            false
        )
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SunflowerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            NETWORK_PAGE_INDEX -> R.drawable.garden_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            NETWORK_PAGE_INDEX -> "网络数据"
            else -> null
        }
    }
}