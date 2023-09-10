package com.example.machines.ui.start_time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.data.repository.StartTimeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartTimeViewModel @Inject constructor(
    val repo: StartTimeRepo
): ViewModel() {

    fun addClayCrusher(machine: ClayCrusherMachine) {
        viewModelScope.launch {
            repo.addClayCrusherItem(machine)
        }
    }

    fun addKiln(machine: KilnMachine) {
        viewModelScope.launch {
            repo.addKilnItem(machine)
        }
    }

    fun addLimestone(machine: LimestoneMachine) {
        viewModelScope.launch {
            repo.addLimestoneItem(machine)
        }
    }

    fun addRawMill(machine: RawMillMachine) {
        viewModelScope.launch {
            repo.addRawMillItem(machine)
        }
    }

    fun addCementMillOne(machine: CementMillMachine1){
        viewModelScope.launch {
            repo.addCementMillItem(machine)
        }
    }

    fun addCementMillTwo(machine: CementMillMachine2){
        viewModelScope.launch {
            repo.addCementTwoItem(machine)
        }
    }
}