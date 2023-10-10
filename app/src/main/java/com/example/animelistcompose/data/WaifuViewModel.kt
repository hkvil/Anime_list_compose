package com.example.animelistcompose.data

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow

class WaifuViewModel(private val application: Context) : ViewModel() {
    private val _waifus = MutableStateFlow(
        getWaifuArrayData(application).sortedBy { it.waifuName }
    )
    val waifus: MutableStateFlow<List<Waifu>> get() = _waifus

    private val _query = mutableStateOf("")

    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _waifus.value = WaifuRepository.searchWaifus(newQuery, application)
    }

    private fun getWaifuByName(name: String, context: Context): Waifu? {
        return WaifuRepository.getWaifuByName(name, context)
    }

    private fun getWaifuArrayData(context: Context): ArrayList<Waifu> {
        return WaifuRepository.getWaifuArrayData(context)
    }
}

class ViewModelFactory(private val application: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaifuViewModel::class.java)) {
            return WaifuViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}