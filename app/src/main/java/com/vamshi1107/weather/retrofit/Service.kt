package com.vamshi1107.weather.retrofit

import com.vamshi1107.weather.models.ResponseData
import retrofit2.http.Headers
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/data/2.5/weather")
      fun getWeather(
        @Query("q") city:String,
        @Query("appid")
        key:String="5814201029ba001e12185ab57c6ddd8e"
     ):Call<ResponseData>
}