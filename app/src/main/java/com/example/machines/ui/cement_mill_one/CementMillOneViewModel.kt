package com.example.machines.ui.cement_mill_one

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.repository.CementMillOneRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CementMillOneViewModel @Inject constructor(
    val repo: CementMillOneRepo
) : ViewModel() {

    fun getAllCementMillOneItems() = repo.getCementMillItems()

    fun deleteCementMillOne(machine: CementMillMachine1) {
        viewModelScope.launch {
            repo.deleteCementMillItem(machine)
        }
    }
}