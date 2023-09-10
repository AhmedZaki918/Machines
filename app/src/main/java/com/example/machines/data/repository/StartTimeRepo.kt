package com.example.machines.data.repository

import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import javax.inject.Inject

class StartTimeRepo @Inject constructor(
    val dao: MachineDao
) {
    suspend fun addLimestoneItem(machine: LimestoneMachine) {
        dao.addLimestone(machine)
    }

    suspend fun addClayCrusherItem(machine: ClayCrusherMachine) {
        dao.addClayCrusher(machine)
    }

    suspend fun addKilnItem(machine: KilnMachine) {
        dao.addKiln(machine)
    }

    suspend fun addRawMillItem(machine: RawMillMachine) {
        dao.addRawMill(machine)
    }

    suspend fun addCementMillItem(cementMill: CementMillMachine1){
        dao.addCementMillOne(cementMill)
    }

    suspend fun addCementTwoItem(cementMill: CementMillMachine2){
        dao.addCementMillTwo(cementMill)
    }
}