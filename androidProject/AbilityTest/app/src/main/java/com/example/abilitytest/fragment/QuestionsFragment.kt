package com.example.abilitytest.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.RadioGroup
import android.widget.TextView
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.example.abilitytest.R
import com.example.abilitytest.databinding.FragmentQuestionsBinding
import com.example.abilitytest.dataroom.FILEPATH
import com.example.abilitytest.utils.MessageUtil
import com.example.abilitytest.utils.Utils
import com.google.android.material.radiobutton.MaterialRadioButton

data class Question(
    val question: String,
    val answer: String,
    val options: List<String>
)

class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var msgUtil: MessageUtil
    private val questionList = listOf(
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
        Question("你是什么垃圾?", "可回收垃圾", listOf("不可回收垃圾", "可回收垃圾", "厨房垃圾", "有害垃圾", "无害垃圾")),
    )
    private val questionStatus = MutableList(questionList.size) { false }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(layoutInflater, container, false)
        msgUtil = MessageUtil(requireContext())

        loadQuestions()

        var hasLearned = 0

        val setProcess = {
            binding.indicatorText.text = "${getString(R.string.learning_progress)} $hasLearned/${questionList.size}"
            binding.indicator.progress = hasLearned * 100 /questionList.size
        }
        setProcess()

        questionList.forEachIndexed { index, question ->
            val context = requireContext()
            val radioGroup = RadioGroup(context).apply {
                setOnCheckedChangeListener { _, _ ->
                    if (!questionStatus[index]) {
                        questionStatus[index] = true
                        hasLearned++
                        setProcess()
                    }
                }
            }

            TextView(context).apply {
                text = "${index + 1}. ${question.question}"
                textSize = 20.0f
                layoutParams = ViewGroup.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT,
                )
                binding.mainLayout.addView(this)
            }

            question.options.forEach {
                MaterialRadioButton(context).apply {
                    text = it
                    textSize = 17.0f
                    layoutParams = ViewGroup.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT,
                    )
                    radioGroup.addView(this)
                }
            }

            binding.mainLayout.addView(radioGroup)
        }
        return binding.root
    }

    fun loadQuestions() {
        val json = Utils.loadFileFromAssets(requireContext(), FILEPATH.QUESTION_JSON_FILE)
        json?.also {
            try {
                val questions = JSON.parseArray<Question>(json)
                questions.forEach {
                    Log.i("yuri", "q: ${it.question}")
                }
            } catch (e: Exception) {
                msgUtil.createErrorDialog("转换失败: -> ${e.message}")
            }

        }
    }
}