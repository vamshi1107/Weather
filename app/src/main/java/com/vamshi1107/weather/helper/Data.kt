package com.vamshi1107.weather.helper

import org.json.JSONObject

interface Data<T> {
    fun Data(data: JSONObject?)
}