package com.example.machines.ui.cement_one

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.repository.CementOneRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CementOneViewModel @Inject constructor(
    val repo: CementOneRepo
) : ViewModel() {

    fun getAllCementMillOneItems() = repo.getCementItems()

    fun deleteCementMillOne(machine: CementMillMachine1) {
        viewModelScope.launch {
            repo.deleteCementItem(machine)
        }
    }
}