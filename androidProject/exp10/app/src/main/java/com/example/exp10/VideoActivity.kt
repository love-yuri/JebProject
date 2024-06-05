package com.example.exp10;

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.SurfaceView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class VideoActivity: AppCompatActivity() {
    private lateinit var videoView: VideoView
    private val videoList = mutableListOf<Video>()
    private var current = -1

    data class Video(
        val title: String,
        val path: String
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.video_layout)
        super.onCreate(savedInstanceState)

        scanVideos()

        videoView = findViewById(R.id.videoView)
        findViewById<ListView>(R.id.listView2).apply {
            adapter = ArrayAdapter(
                this@VideoActivity,
                android.R.layout.simple_list_item_1,
                videoList.map { it.title }
            )
            
            setOnItemClickListener { _, _, position, _ ->
                val video = videoList[position]
                if (current != position) {
                    videoView.setVideoPath(video.path)
                    videoView.start()
                } else {
                    if (videoView.isPlaying) {
                        videoView.pause()
                    } else {
                        videoView.start()
                    }
                }
                current = position
            }
        }
    }

    private fun scanVideos() {
        val cursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val title = it.getString(titleColumn)
                val data = it.getString(dataColumn)
                videoList.add(Video(title, data))
            }
        }
    }
}
