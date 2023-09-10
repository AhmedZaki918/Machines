package com.example.machines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.machines.data.local.Constants.CEMENT_MILL_1_TABLE
import com.example.machines.data.local.Constants.CEMENT_MILL_2_TABLE

@Entity(tableName = CEMENT_MILL_2_TABLE)
data class CementMillMachine2(
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    val startTime: String = "",
    val stopTime: String = "",
    val reason: String = "",
    val rh: String = ""
){
    companion object {
        fun machineName(): String {
            return "Cement Mill 2"
        }
    }
}
