package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.ClayCrusherMachine
import javax.inject.Inject

class ClayCrusherRepo @Inject constructor(
    private val dao: MachineDao
) {

    suspend fun addClayCrusherItem(machine: ClayCrusherMachine) {
        dao.addClayCrusher(machine)
    }

    fun getClayCrusherItems(): LiveData<List<ClayCrusherMachine>> {
        return dao.getAllClayCrusher()
    }

    suspend fun updateClayCrusherItem(machine: ClayCrusherMachine) {
        dao.updateClayCrusher(machine)
    }

    suspend fun deleteClayCrusherItem(machine: ClayCrusherMachine) {
        dao.deleteClayCrusher(machine)
    }
}