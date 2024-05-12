package com.example.exp8


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exp8.UserTable.AppDatabase
import com.example.exp8.UserTable.User
import kotlin.random.Random

class SaveUserActivity : AppCompatActivity() {
    private lateinit var avatar: ImageView
    private lateinit var name: EditText
    private lateinit var number: EditText
    private lateinit var address: EditText
    private lateinit var db: AppDatabase

    private val avatarList = listOf(
        R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04,
        R.drawable.img05, R.drawable.img06, R.drawable.img07,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.save_user)
        super.onCreate(savedInstanceState)

        // 初始化数据
        avatar = findViewById(R.id.image)
        name = findViewById(R.id.editTextUname)
        number = findViewById(R.id.editTextTel)
        address = findViewById(R.id.editTextAddress)

        //点击切换头像
        findViewById<ImageView>(R.id.image)?.apply {
            setOnClickListener {
                val avatar = Random.nextInt(avatarList.size)
                setImageResource(avatarList[avatar])
                tag = avatarList[avatar]
            }
        }

        // 创建数据库
        db = UserTable.getDb(this)
        val userDao = db.userDao()

        val isUpdate = intent.getBooleanExtra("isUpdate", false)
        if (isUpdate) {
            intent.getStringExtra("name")?.let { str ->
                userDao.findByName(str)?.let {
                    name.apply {
                        setText(str)
                        isFocusable = false
                        isFocusableInTouchMode = false
                    }
                    avatar.setImageResource(it.avatar)
                    avatar.tag = it.avatar
                    number.setText(it.number)
                    address.setText(it.address)
                }
            }
        }

        // 保存/更新
        findViewById<Button>(R.id.button2)?.apply {
            var action = userDao::insert
            var str = "保存"
            if (isUpdate) {
                action = userDao::update
                str = "更新"
            }
            text = str
            setOnClickListener {
                execSql(action, str)
            }
        }

        // 重置
        findViewById<Button>(R.id.button)?.apply {
            setOnClickListener {
                if (!isUpdate) {
                    name.apply {
                        setText("")
                    }
                }
                avatar.setImageResource(R.mipmap.ic_launcher)
                number.setText("")
                address.setText("")
            }
        }

        // 退出
        findViewById<Button>(R.id.button3)?.apply {
            setOnClickListener {
                this@SaveUserActivity.finish()
            }
        }
    }

    private fun execSql(f: (User) -> Unit, title: String) {
        try {
            f(User(
                name.text.toString(),
                avatar.tag as Int,
                number.text.toString(),
                address.text.toString()
            ))
            Toast.makeText(this@SaveUserActivity, "${title}成功", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this@SaveUserActivity, "${title}失败, 发生错误 -> ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}