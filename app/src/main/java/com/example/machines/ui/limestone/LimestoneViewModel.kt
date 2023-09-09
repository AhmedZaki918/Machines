package com.example.machines.ui.limestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.repository.LimestoneRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LimestoneViewModel @Inject constructor(
    private val repo: LimestoneRepo
) : ViewModel() {

    fun getAllLimestoneItems() = repo.getLimestoneItems()

    fun deleteLimestone(machine: LimestoneMachine) {
        viewModelScope.launch {
            repo.deleteLimestoneItem(machine)
        }
    }
}