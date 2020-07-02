package com.justso.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.justso.learn.adapter.GardenPlantingAdapter
import com.justso.learn.adapter.PLANT_LIST_PAGE_INDEX
import com.justso.learn.databinding.FragmentGardenBinding
import com.justso.learn.utils.InjectorUtils
import com.justso.learn.viewmodels.GardenPlantingViewModel
import kotlinx.android.synthetic.main.fragment_garden.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class GardenFragment : Fragment() {
    private val gardenPlantViewModel:GardenPlantingViewModel by viewModels {
        InjectorUtils.provideGardenPlantingViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentGardenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        binding.addPlant.setOnClickListener {
            requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
                PLANT_LIST_PAGE_INDEX
        }
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        subscribeUI(adapter,binding)
        return binding.root
    }

    private fun subscribeUI(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        gardenPlantViewModel.plantAndGardenPlantings.observe(viewLifecycleOwner, Observer {
            binding.hasPlantings = !it.isNullOrEmpty()
            adapter.submitList(it)
        })
    }
}