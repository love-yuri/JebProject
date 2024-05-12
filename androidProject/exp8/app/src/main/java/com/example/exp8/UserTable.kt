package com.example.exp8

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

object UserTable {
    private lateinit var db: AppDatabase

    fun getDb(context: Context): AppDatabase {
        if(!::db.isInitialized) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "users.db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
        return db
    }

    @Entity
    data class User(
        @PrimaryKey val name: String,
        @ColumnInfo(name = "avatar") @DrawableRes val avatar: Int,
        @ColumnInfo(name = "number") val number: String,
        @ColumnInfo(name = "address") val address: String,
    )

    @Dao
    interface UserDao {
        @Query("SELECT * FROM user")
        fun getAll(): List<User>

        @Query("SELECT * FROM user where name = :name limit 1")
        fun findByName(name: String): User?

        @Insert
        fun insert(user: User)

        @Update
        fun update(user: User)

        @Delete
        fun delete(user: User)
    }

    @Database(entities = [User::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }
}