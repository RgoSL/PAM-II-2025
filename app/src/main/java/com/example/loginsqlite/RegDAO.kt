package com.example.loginsqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegDAO {
    @Insert
    suspend fun insert(reg: RegEntity)

    @Query("SELECT * FROM registro ORDER BY id DESC")
    suspend fun getAll(): List<RegEntity>
}