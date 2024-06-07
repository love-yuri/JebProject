package com.example.abilitytest.fragment

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.abilitytest.R
import com.example.abilitytest.databinding.FragmentSettingsBinding
import com.example.abilitytest.dataroom.CurrentUser
import com.example.abilitytest.dataroom.FILEPATH
import com.example.abilitytest.dataroom.User
import com.example.abilitytest.dataroom.UserService
import com.example.abilitytest.utils.MessageUtil
import com.example.abilitytest.utils.SharedPreferencesUtil
import com.example.abilitytest.utils.USER_SP
import com.example.abilitytest.utils.Utils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var spUtil: SharedPreferencesUtil
    private lateinit var service: UserService
    private lateinit var msgUtil: MessageUtil

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Utils.copyFile(requireContext(), uri, FILEPATH.AVATAR) {
                it?.also {
                    Glide.with(requireContext()).load(uri).into(binding.avatar)
                    CurrentUser.avatar = it
                } ?: msgUtil.createErrorDialog(getString(R.string.saveError))
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        service = UserService(requireContext())
        spUtil = SharedPreferencesUtil(requireContext())
        msgUtil = MessageUtil(requireContext())

        binding.avatar.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.update.setOnClickListener {
            if (!msgUtil.valueCheck(binding.password.text, getString(R.string.passwordEmpty))) {
                return@setOnClickListener
            }
            CurrentUser.password = binding.password.text.toString()

            service.dao.update(User(
                CurrentUser.username,
                CurrentUser.password,
                CurrentUser.avatar,
            ))
            msgUtil.createToast(getString(R.string.update_success))
        }

        binding.username.setText(CurrentUser.username)
        binding.password.setText(CurrentUser.password)
        Glide.with(binding.root).load(CurrentUser.avatar).into(binding.avatar)

        return binding.root
    }
}