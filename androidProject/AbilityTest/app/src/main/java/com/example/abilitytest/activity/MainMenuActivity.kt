package com.example.abilitytest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.abilitytest.R
import com.example.abilitytest.databinding.ActivityMainBinding
import com.example.abilitytest.fragment.EncyclopediaFragment
import com.example.abilitytest.fragment.NoteFragment
import com.example.abilitytest.fragment.QuestionsFragment
import com.example.abilitytest.fragment.SettingsFragment
import com.example.abilitytest.utils.MessageUtil

class MainMenuActivity : AppCompatActivity() {
    private val msgUtil = MessageUtil(this)
    private lateinit var binding: ActivityMainBinding
    private val fragmentMap = mapOf(
        R.id.questions to QuestionsFragment(),
        R.id.note to NoteFragment(),
        R.id.encyclopedia to EncyclopediaFragment(),
        R.id.settings to SettingsFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        // 设置初始fragment
        changeFragment(fragmentMap[R.id.questions]!!, true)

        // 切换fragment
        binding.bottomNavigation.setOnItemSelectedListener {
            changeFragment(fragmentMap[it.itemId]!!)
            true
        }
    }

    /**
     * 切换fragment
     */
    private fun changeFragment(next: Fragment, isInit: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            if (isInit) {
                add(R.id.frameLayout, next)
            } else {
                replace(R.id.frameLayout, next)
            }
            commit()
        }
    }
}