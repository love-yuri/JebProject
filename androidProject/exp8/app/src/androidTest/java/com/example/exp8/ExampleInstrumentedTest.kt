package com.example.exp8

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import com.example.exp8.UserTable.UserDao
import com.example.exp8.UserTable.AppDatabase
import com.example.exp8.UserTable.User

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = UserTable.getDb(context)
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        userDao.getAll().forEach {
            Log.i("yuri", "name: ${it.name}")
        }
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user = User("hhh", R.drawable.img01, "hhh", "fff")
        userDao.insert(user)
    }
}