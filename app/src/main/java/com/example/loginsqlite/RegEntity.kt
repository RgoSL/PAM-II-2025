package com.example.loginsqlite

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "registro")
data class RegEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val endereco: String,
    val bairro: String,
    val cep: String,
    val obs: String,
    val data: String //Não tem tipo Date em Kotlin, eu não acredito.
)