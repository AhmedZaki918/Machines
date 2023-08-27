package com.example.machines.data.di

import android.app.Application
import androidx.room.Room
import com.example.machines.data.local.MachineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, MachineDatabase::class.java, "machine_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db: MachineDatabase) = db.getMachineDao()
}