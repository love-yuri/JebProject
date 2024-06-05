package com.example.exp10

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class MusicActivity: AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var job: Job
    private lateinit var seekBar: SeekBar
    private lateinit var nextMusic: Button
    private lateinit var frontMusic: Button
    private lateinit var startPauseMusic: Button
    private var currentIndex = 0

    data class Music(
        val title: String,
        val path: String
    )
    private val musicList = mutableListOf<Music>()

    private fun updateSeekBar() {
        if(::job.isInitialized) {
            job.cancel()
        }

        job = lifecycleScope.launch {
            val seekBar: SeekBar = this@MusicActivity.findViewById(R.id.seekBar)
            while (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
                withContext(Dispatchers.Main) {
                    val position = mediaPlayer.currentPosition.toFloat()
                    seekBar.progress = (position / mediaPlayer.duration.toFloat() * 100).toInt()
                }
                delay(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.music_layout)
        super.onCreate(savedInstanceState)

        // 鉴权并扫描音乐
        checkPermissions()

        // init views
        seekBar = findViewById(R.id.seekBar)
        nextMusic = findViewById(R.id.next_music)
        frontMusic = findViewById(R.id.frontMusic)
        startPauseMusic = findViewById(R.id.start_pause)


        findViewById<ListView>(R.id.listView)?.apply {
            val list = musicList.map { it.title }
            adapter = ArrayAdapter(this@MusicActivity, android.R.layout.simple_list_item_1, list)

            setOnItemClickListener { _, _, position, _ ->
                currentIndex = position
                startMusic()
            }

            frontMusic.apply {
                setOnClickListener {
                    if (currentIndex == 0) {
                        currentIndex = musicList.size - 1
                    } else {
                        currentIndex--
                    }
                    startMusic()
                }
            }

            nextMusic.apply {
                setOnClickListener {
                    if (currentIndex == musicList.size - 1) {
                        currentIndex = 0
                    } else {
                        currentIndex++
                    }
                    startMusic()
                }
            }

            // 播放/暂停按钮
            startPauseMusic.apply {
                setOnClickListener {
                    if (::mediaPlayer.isInitialized) {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.pause()
                            job.cancel()
                            text = "开始播放"
                        } else {
                            updateSeekBar()
                            mediaPlayer.start()
                            text = "暂停播放"
                        }
                    } else {
                        Toast.makeText(this@MusicActivity, "请选择一首歌", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        seekBar.apply {
            setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {  }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (::mediaPlayer.isInitialized) {
                        if (!mediaPlayer.isPlaying) {
                            mediaPlayer.start()
                        }
                        mediaPlayer.seekTo(((progress / 100.0f * mediaPlayer.duration).toInt()))
                        startPauseMusic.text = "暂停播放"
                    }
                }
            })
        }
    }

    private fun startMusic() {
        if (!::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer()
        }

        mediaPlayer.apply {
            stop()
            reset()
        }

        try {
            mediaPlayer.setDataSource(musicList[currentIndex].path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            findViewById<TextView>(R.id.musicName)?.apply {
                text = musicList[currentIndex].title
            }
            updateSeekBar()
            startPauseMusic.text = "暂停播放"

        } catch (e: Exception) {
            Toast.makeText(this, "播放错误: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermissions() {
        if (!EasyPermissions.hasPermissions(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(
                this,
                "",
                100,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        } else {
            scanMusicFiles()
        }
    }

    private fun scanMusicFiles() {
//        val musicFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
//        // 读取 Music 文件夹中的所有文件
//        File(musicFolderPath).listFiles { file ->
//            file.isFile && file.extension == "flac"
//        }?.also {
//            MediaScannerConnection.scanFile(this,
//                arrayOf(it.map { file: File? -> file?.absolutePath ?: "" }.toTypedArray().toString()),
//                null
//            ) { _, _ -> }
//        }

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )


        cursor?.use {
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val title = it.getString(titleColumn)
                val data = it.getString(dataColumn)
                musicList.add(Music(title, data))
            }
        }
    }

    override fun onDestroy() {
        if(::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
        super.onDestroy()
    }
}