package com.justso.learn

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.justso.learn.adapter.PlantAdapter
import com.justso.learn.databinding.FragmentPlantListBinding
import com.justso.learn.utils.InjectorUtils
import com.justso.learn.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {
    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val plantAdapter = PlantAdapter()
        binding.plantList.adapter = plantAdapter
        subscribeUI(plantAdapter)
        return binding.root
    }

    private fun subscribeUI(plantAdapter: PlantAdapter) {
        viewModel.plants.observe(this, Observer {
            plantAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plant_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}