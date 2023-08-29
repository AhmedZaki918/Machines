package com.example.machines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.machines.data.model.MachineMain

@Database(
    entities = [MachineMain::class],
    version = 2, exportSchema = false
)
abstract class MachineDatabase : RoomDatabase(){
    abstract fun getMachineDao(): MachineDao
}