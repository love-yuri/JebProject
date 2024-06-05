package com.example.abilitytest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.abilitytest.R
import com.example.abilitytest.databinding.LoginLayoutBinding
import com.example.abilitytest.utils.MessageUtil
import com.google.android.material.button.MaterialButton

class LoginActivity: AppCompatActivity() {
    enum class Mode {
        LOGIN,
        REGISTER
    }

    private var runMode = Mode.LOGIN
    private lateinit var binding: LoginLayoutBinding
    private val messageUtil = MessageUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = LoginLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        // 获取运行状态
        runMode = intent.getStringExtra("mode")?.let(Mode::valueOf) ?: Mode.REGISTER
        // 检测登录状态
        binding.loginRegister.apply {
            text = when(runMode){
                Mode.LOGIN -> getString(R.string.login)
                Mode.REGISTER -> getString(R.string.register)
            }

            setOnClickListener {
                when(runMode) {
                    Mode.LOGIN -> login()
                    Mode.REGISTER -> register()
                }
            }
        }
    }

    private fun login() {
        messageUtil.createToast("Login Success!")
    }

    private fun register() {
        messageUtil.createToast("Register Success!")
    }
}