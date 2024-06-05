package com.example.abilitytest.utils

import android.content.Context
import android.widget.Toast

class MessageUtil(
    private val context: Context
) {
    fun createToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun valueCheck(id : Int) {
//        val view =
    }
}