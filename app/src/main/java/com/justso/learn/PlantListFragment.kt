package com.justso.learn

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.justso.learn.adapter.PlantAdapter
import com.justso.learn.databinding.FragmentPlantListBinding

class PlantListFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater,container,false)
        val plantAdapter = PlantAdapter()
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.plant_list_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}