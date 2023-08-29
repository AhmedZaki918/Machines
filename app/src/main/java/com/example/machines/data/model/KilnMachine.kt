package com.example.machines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.machines.data.local.Constants.KILN_TABLE

@Entity(tableName = KILN_TABLE)
data class KilnMachine(
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    val startTime: String = "",
    val stopTime: String = "",
    val reason: String = "",
    val rh: String = ""
)
