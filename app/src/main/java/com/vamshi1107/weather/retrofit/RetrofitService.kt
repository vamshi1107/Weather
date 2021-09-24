package com.vamshi1107.weather.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    var Service=  Retrofit.Builder()
                  .addConverterFactory(GsonConverterFactory.create())
                  .baseUrl("http://api.openweathermap.org/")
                  .build().create(com.vamshi1107.weather.retrofit.Service::class.java)
}