package com.example.machines.data.local

import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.LimestoneMachine

object Constants {
    const val DEFAULT_VALUE = "00:00"
    const val DEFAULT_HOUR = "0"
    const val TIME_FORMAT = "H:mm"
    const val R_H_RESET = "00:00"
    const val RUNNING = "------->"
    const val EMPTY = ""
    const val SEVEN_AM = "07:00"
    const val TWENTY_FOUR = "24:00"
    const val COLUMN = ":"
    const val DOT = "."
    const val MINUTES_RESET = "00"

    var machine = LimestoneMachine()
    var clayCrusherMachine = ClayCrusherMachine()

    var RH_LIMESTONE = ""
    var RH_CLAY_CRUSHER = ""

    const val LIMESTONE_TABLE = "Limestone"
    const val CLAY_CRUSHER_TABLE = "ClayCrusher"
    const val RAW_MILL_TABLE = "RawMill"
    const val KILN_TABLE = "Kiln"
    const val CEMENT_MILL_1_TABLE = "CementMill_1"
    const val CEMENT_MILL_2_TABLE = "CementMill_2"
    const val CEMENT_MILL_3_TABLE = "CementMill_3"

    var machineType = ""
}