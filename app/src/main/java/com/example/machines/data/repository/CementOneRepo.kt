package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine1
import javax.inject.Inject

class CementOneRepo @Inject constructor(
    val dao: MachineDao
) {

    suspend fun addCementItem(cementMill: CementMillMachine1){
        dao.addCementMillOne(cementMill)
    }

    fun getCementItems(): LiveData<List<CementMillMachine1>> {
        return dao.getAllCementMillOne()
    }

    suspend fun updateCementItem(cementMill: CementMillMachine1){
        dao.updateCementMillOne(cementMill)
    }

    suspend fun deleteCementItem(cementMill: CementMillMachine1){
        dao.deleteCementMillOne(cementMill)
    }
}