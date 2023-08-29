package com.example.machines.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.machines.data.local.Constants.CLAY_CRUSHER_TABLE
import com.example.machines.data.local.Constants.LIMESTONE_TABLE
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.LimestoneMachine

@Dao
interface MachineDao {

    // Limestone Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLimestone(limestone: LimestoneMachine)

    @Query("SELECT * FROM $LIMESTONE_TABLE")
    fun getAllLimestone(): LiveData<List<LimestoneMachine>>

    @Update
    suspend fun updateLimestone(limestone: LimestoneMachine)

    @Delete
    suspend fun deleteLimestone(limestone: LimestoneMachine)


    // Clay Crusher Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClayCrusher(clayCrusher: ClayCrusherMachine)

    @Query("SELECT * FROM $CLAY_CRUSHER_TABLE")
    fun getAllClayCrusher(): LiveData<List<ClayCrusherMachine>>

    @Update
    suspend fun updateClayCrusher(clayCrusher: ClayCrusherMachine)

    @Delete
    suspend fun deleteClayCrusher(clayCrusher: ClayCrusherMachine)
}