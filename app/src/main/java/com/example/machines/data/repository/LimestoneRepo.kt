package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.MachineMain
import javax.inject.Inject

class LimestoneRepo @Inject constructor(
    private val dao: MachineDao
) {
    suspend fun addLimestoneItem(machine: MachineMain) {
        dao.addLimestone(machine)
    }

    fun getLimestoneItems(): LiveData<List<MachineMain>> {
        return dao.getAllLimestone()
    }

    suspend fun updateLimestoneItem(machine: MachineMain){
        dao.updateMachine(machine)
    }
}