package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import javax.inject.Inject

class ReportRepo @Inject constructor(
    private val dao: MachineDao
) {

    fun getLimestoneReport(): LiveData<List<LimestoneMachine>> {
        return dao.getAllLimestone()
    }

    fun getClayCrusherReport(): LiveData<List<ClayCrusherMachine>> {
        return dao.getAllClayCrusher()
    }

    fun getRawMillReport(): LiveData<List<RawMillMachine>> {
        return dao.getAllRawMill()
    }

    fun getKilnReport(): LiveData<List<KilnMachine>> {
        return dao.getAllKiln()
    }

    fun getCementMillReport(): LiveData<List<CementMillMachine1>> {
        return dao.getAllCementMillOne()
    }

    fun getCementTwoReport(): LiveData<List<CementMillMachine2>> {
        return dao.getAllCementMillTwo()
    }

    fun getCementThreeReport(): LiveData<List<CementMillMachine3>> {
        return dao.getAllCementMillThree()
    }
}