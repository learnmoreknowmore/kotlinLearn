package com.justso.learn.viewmodels

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.justso.learn.data.NetworkRepository

@Suppress("UNCHECKED_CAST")
class NetworkViewModelFactory(context: Fragment,private val repository: NetworkRepository):
    AbstractSavedStateViewModelFactory(context,null) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return NetworkViewModel(repository,handle) as T
    }

}