package com.example.machines.ui.stop_time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.data.repository.StopTimeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopTimeViewModel @Inject constructor(
    val repo: StopTimeRepo
) : ViewModel() {

    fun updateKiln(machine: KilnMachine) {
        viewModelScope.launch {
            repo.updateKilnItem(machine)
        }
    }

    fun updateLimestone(machine: LimestoneMachine) {
        viewModelScope.launch {
            repo.updateLimestoneItem(machine)
        }
    }

    fun updateRawMill(machine: RawMillMachine) {
        viewModelScope.launch {
            repo.updateRawMillItem(machine)
        }
    }

    fun updateClayCrusher(machine: ClayCrusherMachine) {
        viewModelScope.launch {
            repo.updateClayCrusherItem(machine)
        }
    }

    fun updateCementMillOne(machine: CementMillMachine1){
        viewModelScope.launch {
            repo.updateCementMillItem(machine)
        }
    }

    fun updateCementMillTwo(machine: CementMillMachine2){
        viewModelScope.launch {
            repo.updateCementTwoItem(machine)
        }
    }
}