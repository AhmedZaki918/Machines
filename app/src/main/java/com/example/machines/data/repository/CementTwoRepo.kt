package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine2
import javax.inject.Inject

class CementTwoRepo @Inject constructor(
    val dao: MachineDao
) {

    suspend fun addCementTwoItem(cementMill: CementMillMachine2){
        dao.addCementMillTwo(cementMill)
    }

    fun getCementTwoItems(): LiveData<List<CementMillMachine2>> {
        return dao.getAllCementMillTwo()
    }

    suspend fun updateCementTwoItem(cementMill: CementMillMachine2){
        dao.updateCementMillTwo(cementMill)
    }

    suspend fun deleteCementTwoItem(cementMill: CementMillMachine2){
        dao.deleteCementMillTwo(cementMill)
    }
}