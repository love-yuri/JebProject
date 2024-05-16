package com.example.exp9

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginStart
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        val width = windowManager.defaultDisplay.width
        var count = 40
        var isRunning = false
        textView?.apply {
            "$count%".also { text = it }
            translationX = (count / 100.0f * width) - 80
        }

        findViewById<Button>(R.id.download)?.apply {
            var job : Job? = null
            setOnClickListener {
                if (!isRunning) {
                    text = "暂停下载"
                    job = lifecycleScope.launch {
                        while (count < progressBar.max && isActive) {
                            delay(1000)
                            count++
                            withContext(Dispatchers.Main) {
                                textView?.apply {
                                    // 重新设置百分比
                                    "$count%".also { text = it }
                                    // 移动进度文字
                                    translationX = (count / 100.0f * width) - 80
                                }
                                progressBar?.setProgress(count)
                            }
                        }
                    }
                } else {
                    job?.apply {
                        text = "开始下载"
                        cancel()
                    }
                }
                isRunning = !isRunning
            }
        }

        val lightLayout = findViewById<RelativeLayout>(R.id.light_layout)
        val lights = mutableListOf<View>()
        for (i in 0..5) {
            val tt = View(this).apply {
                layoutParams = RelativeLayout.LayoutParams(
                    width - 80 - i * 160,
                    width - 80 - i * 160
                )
                val params = layoutParams as RelativeLayout.LayoutParams
                params.setMargins(40 + i * 80, 40 + i * 80, 0, 0)
                params.addRule(RelativeLayout.BELOW, R.id.light)

                setBackgroundColor(generateRandomColor())
            }
            lights.add(tt)
            lightLayout.addView(tt)
        }

        var isLightRunning = false
        var lightsJob: Job? = null

        findViewById<Button>(R.id.light)?.apply {
            setOnClickListener {
                if (!isLightRunning) {
                    text = "暂停闪烁"
                    lightsJob = lifecycleScope.launch {
                        while (isActive) {
                            lights.forEach {
                                delay(500)
                                withContext(Dispatchers.Main) {
                                    it.setBackgroundColor(generateRandomColor())
                                }
                            }
                        }
                    }
                } else {
                    lightsJob?.apply {
                        text = "开始闪烁"
                        cancel() // 取消协程
                    }
                }
                isLightRunning = !isLightRunning
            }
        }
    }

    private fun generateRandomColor(): Int {
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)
        return Color.rgb(r, g, b)
    }
}