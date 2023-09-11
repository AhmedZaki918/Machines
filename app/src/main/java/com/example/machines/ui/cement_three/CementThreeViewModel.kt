package com.example.machines.ui.cement_three

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.data.repository.CementThreeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CementThreeViewModel @Inject constructor(
    val repo: CementThreeRepo
) : ViewModel() {

    fun getAllCementThreeItems() = repo.getCementThreeItems()

    fun deleteCementThree(machine: CementMillMachine3) {
        viewModelScope.launch {
            repo.deleteCementThreeItem(machine)
        }
    }
}