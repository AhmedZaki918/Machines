package com.example.machines.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.machines.data.local.Constants.LIMESTONE_TABLE
import com.example.machines.data.model.MachineMain

@Dao
interface MachineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLimestone(machine: MachineMain)

    @Query("SELECT * FROM $LIMESTONE_TABLE")
    fun getAllLimestone(): LiveData<List<MachineMain>>

    @Update
    suspend fun updateMachine(machine: MachineMain)

    @Delete
    suspend fun deleteItem(machine: MachineMain)
}