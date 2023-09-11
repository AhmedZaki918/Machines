package com.example.machines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.machines.data.local.Constants.CEMENT_MILL_3_TABLE

@Entity(tableName = CEMENT_MILL_3_TABLE)
data class CementMillMachine3(
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    val startTime: String = "",
    val stopTime: String = "",
    val reason: String = "",
    val rh: String = ""
) {
    companion object {
        fun machineName(): String {
            return "Cement Mill 3"
        }
    }
}
