package com.example.machines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine

@Database(
    entities = [LimestoneMachine::class, ClayCrusherMachine::class, RawMillMachine::class,
        KilnMachine::class, CementMillMachine1::class, CementMillMachine2::class,
        CementMillMachine3::class],
    version = 1, exportSchema = false
)
abstract class MachineDatabase : RoomDatabase() {
    abstract fun getMachineDao(): MachineDao
}