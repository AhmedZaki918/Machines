package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import javax.inject.Inject

class KilnRepo @Inject constructor(
    private val dao: MachineDao
) {

    suspend fun addKilnItem(machine: KilnMachine) {
        dao.addKiln(machine)
    }

    fun getKilnItems(): LiveData<List<KilnMachine>> {
        return dao.getAllKiln()
    }

    suspend fun updateKilnItem(machine: KilnMachine) {
        dao.updateKiln(machine)
    }

    suspend fun deleteKilnItem(machine: KilnMachine) {
        dao.deleteKiln(machine)
    }
}