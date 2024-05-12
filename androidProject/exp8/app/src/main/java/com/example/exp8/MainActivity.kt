package com.example.exp8

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Printer
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.exp8.UserTable.User
import com.example.exp8.UserTable.AppDatabase

class MainActivity : AppCompatActivity() {
    private var userList = mutableListOf<User>()
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 创建数据库
        db = UserTable.getDb(this)


        // 添加数据
        findViewById<ImageView>(R.id.add)?.apply {
            setOnClickListener {
                this@MainActivity.startActivity(Intent(this@MainActivity, SaveUserActivity::class.java))
            }
        }

        // 更新/删除 菜单
        findViewById<ListView>(R.id.listView)?.apply {
            this@MainActivity.registerForContextMenu(this)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            R.id.delete -> {
                try {
                    db.userDao().delete(userList[position])
                    userList.removeAt(position)
                    findViewById<ListView>(R.id.listView)?.apply {
                        adapter = ListViewAdapter()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "发生错误 -> ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.update -> {
                val name = userList[position].name
                startActivity(Intent(this, SaveUserActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("isUpdate", true)
                })
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        val userDao = db.userDao()
        userList = userDao.getAll().toMutableList()
        findViewById<ListView>(R.id.listView)?.apply {
            adapter = ListViewAdapter()
        }
        super.onResume()
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }

    inner class ListViewAdapter: BaseAdapter() {
        override fun getCount(): Int {
            return userList.size
        }

        override fun getItem(position: Int): User {
            return userList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(this@MainActivity).inflate(R.layout.list_view_layout, parent, false)
            val item = getItem(position)

            view.apply {
                findViewById<ImageView>(R.id.imageView)?.setImageResource(item.avatar)
                findViewById<TextView>(R.id.textViewTop)?.text = item.name
                findViewById<TextView>(R.id.textViewBottom)?.text = item.number
            }

            return view
        }
    }
}