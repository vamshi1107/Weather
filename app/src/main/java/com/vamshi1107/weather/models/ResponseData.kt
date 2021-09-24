package com.vamshi1107.weather.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData(

    @SerializedName("main")
    @Expose
    val main: Main,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("visibility")
    @Expose
    val visibility: Int,

    @SerializedName("weather")
    @Expose
    val weather: List<WeatherX>,

    @SerializedName("wind")
    @Expose
    val wind: Wind,

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds,

)