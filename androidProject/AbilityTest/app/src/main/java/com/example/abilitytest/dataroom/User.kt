package com.example.abilitytest.dataroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "username") val password: String,
)