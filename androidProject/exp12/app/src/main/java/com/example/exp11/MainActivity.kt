package com.example.exp11

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val cityList = listOf(
        "常熟",
        "常州",
        "大理",
        "扬州",
        "绵阳",
        "自贡",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ListView>(R.id.listView)?.apply {
            adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, cityList)

            setOnItemClickListener { _, _, position, _ ->
                this@MainActivity.startActivity(Intent(this@MainActivity, WeatherActivity::class.java).apply {
                    putExtra("city", cityList[position])
                })
            }
        }
    }
}