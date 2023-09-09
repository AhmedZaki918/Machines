package com.example.machines.ui.claycrusher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.repository.ClayCrusherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClayCrusherViewModel @Inject constructor(
    private val repo: ClayCrusherRepo
) : ViewModel() {

    fun getAllClayCrusherItems() = repo.getClayCrusherItems()

    fun deleteClayCrusher(machine: ClayCrusherMachine) {
        viewModelScope.launch {
            repo.deleteClayCrusherItem(machine)
        }
    }
}