package com.example.machines.data.model

data class MachineMain(
    val id: Short = 0,
    val machineName: String = "",
    val startTime: String = "",
    val stopTime: String = "",
    val runningStatus: Boolean = false,
    val reason: String = "",
    val rh: String = "",
    val rhTotal: String = "",
)
