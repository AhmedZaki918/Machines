package com.example.machines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.machines.data.local.Constants.RAW_MILL_TABLE

@Entity(tableName = RAW_MILL_TABLE)
data class RawMillMachine(
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    val startTime: String = "",
    val stopTime: String = "",
    val reason: String = "",
    val rh: String = ""
){
    companion object {
        fun machineName(): String {
            return "Raw Mill"
        }
    }
}
