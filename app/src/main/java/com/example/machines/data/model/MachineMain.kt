package com.example.machines.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.machines.data.local.Constants.LIMESTONE_TABLE

@Entity(tableName = LIMESTONE_TABLE)
data class MachineMain (
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    val startTime: String = "",
    val stopTime: String = "",
    val reason: String = "",
    val rh: String = "",
    val rhTotal: String = ""
)
