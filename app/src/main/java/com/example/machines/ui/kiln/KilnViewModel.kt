package com.example.machines.ui.kiln

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.repository.KilnRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KilnViewModel @Inject constructor(
    private val repo: KilnRepo
) : ViewModel() {


    fun addKiln(machine: KilnMachine) {
        viewModelScope.launch {
            repo.addKilnItem(machine)
        }
    }

    fun getAllKilnItems() = repo.getKilnItems()

    fun updateKiln(machine: KilnMachine) {
        viewModelScope.launch {
            repo.updateKilnItem(machine)
        }
    }

    fun deleteKiln(machine: KilnMachine) {
        viewModelScope.launch {
            repo.deleteKilnItem(machine)
        }
    }
}