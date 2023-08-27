package com.example.machines.ui.limestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.MachineMain
import com.example.machines.data.repository.LimestoneRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LimestoneViewModel @Inject constructor(
    private val repo: LimestoneRepo
) : ViewModel() {

    fun addLimestone(machine: MachineMain) {
        viewModelScope.launch {
            repo.addLimestoneItem(machine)
        }
    }

    fun getAllLimestoneItems() = repo.getLimestoneItems()

    fun updateLimestone(machine: MachineMain) {
        viewModelScope.launch {
            repo.updateLimestoneItem(machine)
        }
    }
}