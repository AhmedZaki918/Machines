package com.example.machines.data.model

import com.example.machines.utils.Constants.machineMains

data class MachineMain(
    val id: Short = 0,
    val machineName: String = "",
    val runningTime: RunningTime,
    val reason: Reason? = null,
    val rh: RH? = null,
    val rhTotal: String = "",
){


    fun updateItem(id: Short){

        for (x in machineMains.indices){
            if (id == machineMains[x].id){

                //machineMains.set(x,)
                break
            }
        }
    }
}
