package com.justso.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justso.learn.databinding.FragmentGardenBinding

class GardenFragment :Fragment() {
    private lateinit var binding: FragmentGardenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        return binding.root
    }
}