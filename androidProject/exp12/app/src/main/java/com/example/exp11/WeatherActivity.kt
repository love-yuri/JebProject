package com.example.exp11

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity: AppCompatActivity() {

    inner class ListViewAdapter: BaseAdapter() {
        override fun getCount(): Int {
            return hours.size
        }

        override fun getItem(position: Int): HoursAdapter {
            return hours[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(this@WeatherActivity).inflate(R.layout.weather_list_item_layout, parent, false)
            val item = getItem(position)

            view.findViewById<TextView>(R.id.hours).text = item.hours
            view.findViewById<TextView>(R.id.wea).text = item.wea
            view.findViewById<TextView>(R.id.tem).text = item.tem
            view.findViewById<ImageView>(R.id.img_tem).apply {
                setImageResource(item.wea_img)
            }
1
            return view
        }

    }

    private var hours = listOf<HoursAdapter>()
    private val iconMap = mapOf(
        "yun" to R.drawable.yun,
        "xue" to R.drawable.xue,
        "lei" to R.drawable.lei,
        "shachen" to R.drawable.shacheng,
        "wu" to R.drawable.wu,
        "bingbao" to R.drawable.bingbao,
        "yu" to R.drawable.yu,
        "yin" to R.drawable.yin,
        "qing" to R.drawable.qing,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)

        val city = intent.getStringExtra("city") ?: "常熟"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://v0.yiketianqi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                service.weather(city).enqueue(object :retrofit2.Callback<Weather> {
                    // 接收到信息
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        val weather = response.body() as Weather


                        this@WeatherActivity.findViewById<TextView>(R.id.tv_city).text = weather.city
                        this@WeatherActivity.findViewById<TextView>(R.id.tv_temp).text = weather.tem
                        this@WeatherActivity.findViewById<TextView>(R.id.tv_hum).text = weather.humidity
                        this@WeatherActivity.findViewById<TextView>(R.id.tv_wind).text = weather.win
                        this@WeatherActivity.findViewById<TextView>(R.id.tv_city).text = weather.city
                        this@WeatherActivity.findViewById<TextView>(R.id.tv_updtime).text = weather.update_time

                        this@WeatherActivity.findViewById<ListView>(R.id.list_weather).apply {
                            hours = weather.hours.map { HoursAdapter(
                                it.hours,
                                it.wea,
                                it.tem,
                                iconMap[it.wea_img] ?: R.drawable.qing
                            ) }
                            adapter = ListViewAdapter()
                        }

                    }

                    // 调用失败
                    override fun onFailure(call: Call<Weather>, t: Throwable) {
                        TODO("调用失败: ${t.message}")
                    }
                })
            }

        }

    }
}