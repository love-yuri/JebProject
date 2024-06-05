package com.example.exp11

import androidx.annotation.DrawableRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
data class Hours(
    val hours: String,
    val wea: String,
    val wea_img: String,
    val tem: String,
)
data class HoursAdapter(
    val hours: String,
    val wea: String,
    val tem: String,
    @DrawableRes val wea_img: Int,
)
data class Weather(

    val date: String,
    val city: String,
    val wea_img: String,
    val tem: String,
    val humidity: String,
    val win: String,
    val update_time: String,
    val hours: List<Hours>
)

interface RetrofitService {
    @GET("api?unescape=1&version=v63&appid=53693496&appsecret=FujqLY9b")
    fun weather(@Query("city") city: String): Call<Weather>
}