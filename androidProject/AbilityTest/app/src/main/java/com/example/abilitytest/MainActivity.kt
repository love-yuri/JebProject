package com.example.abilitytest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import com.example.abilitytest.activity.MainMenuActivity
import com.example.abilitytest.databinding.WelcomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: WelcomeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        lifecycleScope.launch {
            val start = {
                this@MainActivity.startActivity(Intent(this@MainActivity, MainMenuActivity::class.java))
                this@MainActivity.finish()
            }
            val skipBtn = binding.skip .apply {
                setOnClickListener {
                    start()
                }
            }

            var defaultWaitTime = 6
            while (defaultWaitTime --> 0) {
                withContext(Dispatchers.Main) {
                    skipBtn.text = "跳过 (${defaultWaitTime}s)"
                }
                delay(1000)
            }
            start()
        }
    }
}