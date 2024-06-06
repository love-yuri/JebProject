package com.example.abilitytest.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.abilitytest.R
import com.example.abilitytest.databinding.FragmentNoteBinding
import com.example.abilitytest.utils.MessageUtil

class NoteFragment: Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var msgUtil: MessageUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        msgUtil = MessageUtil(requireContext())

        binding.write.apply {
            setOnClickListener {
                msgUtil.createToast("hhhh")
            }
        }
        return binding.root
    }
}