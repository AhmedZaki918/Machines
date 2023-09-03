package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.RawMillMachine
import javax.inject.Inject

class RawMillRepo @Inject constructor(
    private val dao: MachineDao
) {

    suspend fun addRawMillItem(machine: RawMillMachine) {
        dao.addRawMill(machine)
    }

    fun getRawMillItems(): LiveData<List<RawMillMachine>> {
        return dao.getAllRawMill()
    }

    suspend fun updateRawMillItem(machine: RawMillMachine) {
        dao.updateRawMill(machine)
    }

    suspend fun deleteRawMillItem(machine: RawMillMachine) {
        dao.deleteRawMill(machine)
    }
}