package com.example.abilitytest.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.URI
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

object Utils {
    fun copyFile(file: File, path: String) {
        val newFile = File(path).apply {
            parentFile?.let {
                if (!it.exists()) {
                    it.mkdirs()
                }
            }
            if (!exists()) {
                createNewFile()
            }
        }
        try {
            file.inputStream().use { input ->
                newFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("yuri", "复制文件错误: ${e.message}")
        }
    }

    fun now(): String {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    fun copyUriToLocalFile(context: Context, uri: Uri, destinationPath: String): Boolean {
        val destinationFile = File(destinationPath).apply {
            parentFile?.let {
                if (!it.exists()) {
                    it.mkdirs()
                }
            }
            if (!exists()) {
                createNewFile()
            }
        }

        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(destinationFile).use { outputStream ->
                    copyStream(inputStream, outputStream)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun copyStream(input: InputStream, output: OutputStream) {
        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (input.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
    }
}