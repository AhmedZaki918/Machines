package com.example.machines.ui.cement_two

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.repository.CementTwoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CementTwoViewModel @Inject constructor(
    val repo: CementTwoRepo
) : ViewModel() {

    fun getAllCementTwoItems() = repo.getCementTwoItems()

    fun deleteCementTwo(machine: CementMillMachine2) {
        viewModelScope.launch {
            repo.deleteCementTwoItem(machine)
        }
    }
}