package com.example.machines.ui.raw_mill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.RawMillMachine
import com.example.machines.data.repository.RawMillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RawMillViewModel @Inject constructor(
    private val repo: RawMillRepo
) : ViewModel() {

    fun getAllRawMillItems() = repo.getRawMillItems()

    fun deleteRawMill(machine: RawMillMachine) {
        viewModelScope.launch {
            repo.deleteRawMillItem(machine)
        }
    }
}