package com.example.machines.data.repository

import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import javax.inject.Inject

class StopTimeRepo @Inject constructor(
    val dao: MachineDao
) {

    suspend fun updateClayCrusherItem(machine: ClayCrusherMachine) {
        dao.updateClayCrusher(machine)
    }

    suspend fun updateKilnItem(machine: KilnMachine) {
        dao.updateKiln(machine)
    }

    suspend fun updateLimestoneItem(machine: LimestoneMachine) {
        dao.updateLimestone(machine)
    }

    suspend fun updateRawMillItem(machine: RawMillMachine) {
        dao.updateRawMill(machine)
    }

    suspend fun updateCementMillItem(cementMill: CementMillMachine1){
        dao.updateCementMillOne(cementMill)
    }
}