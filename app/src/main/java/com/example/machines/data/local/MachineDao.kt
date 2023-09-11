package com.example.machines.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.machines.data.local.Constants.CEMENT_MILL_1_TABLE
import com.example.machines.data.local.Constants.CEMENT_MILL_2_TABLE
import com.example.machines.data.local.Constants.CEMENT_MILL_3_TABLE
import com.example.machines.data.local.Constants.CLAY_CRUSHER_TABLE
import com.example.machines.data.local.Constants.KILN_TABLE
import com.example.machines.data.local.Constants.LIMESTONE_TABLE
import com.example.machines.data.local.Constants.RAW_MILL_TABLE
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine

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


    // Raw Mill Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRawMill(rawMill: RawMillMachine)

    @Query("SELECT * FROM $RAW_MILL_TABLE")
    fun getAllRawMill(): LiveData<List<RawMillMachine>>

    @Update
    suspend fun updateRawMill(rawMill: RawMillMachine)

    @Delete
    suspend fun deleteRawMill(rawMill: RawMillMachine)


    // Kiln Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKiln(kiln: KilnMachine)

    @Query("SELECT * FROM $KILN_TABLE")
    fun getAllKiln(): LiveData<List<KilnMachine>>

    @Update
    suspend fun updateKiln(kiln: KilnMachine)

    @Delete
    suspend fun deleteKiln(kiln: KilnMachine)


    // Cement Mill 1 Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCementMillOne(cementMillOne: CementMillMachine1)

    @Query("SELECT * FROM $CEMENT_MILL_1_TABLE")
    fun getAllCementMillOne(): LiveData<List<CementMillMachine1>>

    @Update
    suspend fun updateCementMillOne(cementMillOne: CementMillMachine1)

    @Delete
    suspend fun deleteCementMillOne(cementMillOne: CementMillMachine1)


    // Cement Mill 2 Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCementMillTwo(cementMillOne: CementMillMachine2)

    @Query("SELECT * FROM $CEMENT_MILL_2_TABLE")
    fun getAllCementMillTwo(): LiveData<List<CementMillMachine2>>

    @Update
    suspend fun updateCementMillTwo(cementMillOne: CementMillMachine2)

    @Delete
    suspend fun deleteCementMillTwo(cementMillOne: CementMillMachine2)


    // Cement Mill 3 Machine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCementMillThree(cementMillOne: CementMillMachine3)

    @Query("SELECT * FROM $CEMENT_MILL_3_TABLE")
    fun getAllCementMillThree(): LiveData<List<CementMillMachine3>>

    @Update
    suspend fun updateCementMillThree(cementMillOne: CementMillMachine3)

    @Delete
    suspend fun deleteCementMillThree(cementMillOne: CementMillMachine3)
}