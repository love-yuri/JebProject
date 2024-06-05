package com.example.abilitytest.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.abilitytest.R
import com.example.abilitytest.databinding.ActivityMainBinding
import com.example.abilitytest.utils.MessageUtil

class MainMenuActivity: AppCompatActivity() {
    private val messageUtil = MessageUtil(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            else -> {
                messageUtil.createToast(item.title.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
    }
}