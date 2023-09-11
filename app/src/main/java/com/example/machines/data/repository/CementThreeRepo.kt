package com.example.machines.data.repository

import androidx.lifecycle.LiveData
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.CementMillMachine3
import javax.inject.Inject

class CementThreeRepo @Inject constructor(
    val dao: MachineDao
) {

    suspend fun addCementThreeItem(cementMill: CementMillMachine3) {
        dao.addCementMillThree(cementMill)
    }

    fun getCementThreeItems(): LiveData<List<CementMillMachine3>> {
        return dao.getAllCementMillThree()
    }

    suspend fun updateCementThreeItem(cementMill: CementMillMachine3) {
        dao.updateCementMillThree(cementMill)
    }

    suspend fun deleteCementThreeItem(cementMill: CementMillMachine3) {
        dao.deleteCementMillThree(cementMill)
    }
}