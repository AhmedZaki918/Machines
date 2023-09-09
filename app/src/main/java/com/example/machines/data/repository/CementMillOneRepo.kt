package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine1
import javax.inject.Inject

class CementMillOneRepo @Inject constructor(
    val dao: MachineDao
) {

    suspend fun addCementMillItem(cementMill: CementMillMachine1){
        dao.addCementMillOne(cementMill)
    }

    fun getCementMillItems(): LiveData<List<CementMillMachine1>> {
        return dao.getAllCementMillOne()
    }

    suspend fun updateCementMillItem(cementMill: CementMillMachine1){
        dao.updateCementMillOne(cementMill)
    }

    suspend fun deleteCementMillItem(cementMill: CementMillMachine1){
        dao.deleteCementMillOne(cementMill)
    }
}