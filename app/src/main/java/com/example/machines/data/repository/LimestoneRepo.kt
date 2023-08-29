package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.LimestoneMachine
import javax.inject.Inject

class LimestoneRepo @Inject constructor(
    private val dao: MachineDao
) {
    suspend fun addLimestoneItem(machine: LimestoneMachine) {
        dao.addLimestone(machine)
    }

    fun getLimestoneItems(): LiveData<List<LimestoneMachine>> {
        return dao.getAllLimestone()
    }

    suspend fun updateLimestoneItem(machine: LimestoneMachine) {
        dao.updateLimestone(machine)
    }

    suspend fun deleteLimestoneItem(machine: LimestoneMachine) {
        dao.deleteLimestone(machine)
    }
}