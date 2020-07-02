package com.justso.learn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.justso.learn.HomeViewPagerFragmentDirections
import com.justso.learn.R
import com.justso.learn.data.GardenPlanting
import com.justso.learn.data.PlantAndGardenPlantings
import com.justso.learn.databinding.FragmentPlantDetailBinding
import com.justso.learn.databinding.ListItemGardenPlantingBinding
import com.justso.learn.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter:ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.PlantViewHolder>(GardenPlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_garden_planting,parent,false))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class PlantViewHolder(private val binding: ListItemGardenPlantingBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {view->
                binding.viewModel?.plantId?.let {plantId->
                    navigationToPlant(plantId,view)
                }

            }
        }
        private fun navigationToPlant(plantId:String, view:View){
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }
        fun bind(item:PlantAndGardenPlantings){
            with(binding){
                viewModel = PlantAndGardenPlantingsViewModel(item)
                executePendingBindings()
            }
        }
    }
    class GardenPlantDiffCallback: DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
        override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
            return oldItem.plant.plantId == newItem.plant.plantId
        }

        override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
            return oldItem == newItem
        }
    }
}