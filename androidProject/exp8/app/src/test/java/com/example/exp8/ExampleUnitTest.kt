package com.example.exp8

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    private lateinit var userDao: SaveUserActivity.UserDao
    private lateinit var db: SaveUserActivity.AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SaveUserActivity.AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        userDao.getAll().forEach {
            Log.i("yuri", "name: ${it.firstName}")
        }
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: SaveUserActivity.User = SaveUserActivity.User(2, "hhh", "fff")
        userDao.insertAll(user)
        val byName = userDao.findByName("george")
        MatcherAssert.assertThat(byName, CoreMatchers.equalTo(user))
    }
}