package com.vamshi1107.weather


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response

import com.android.volley.VolleyError

import org.json.JSONObject

import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vamshi1107.weather.helper.Data
import com.vamshi1107.weather.models.ResponseData
import kotlin.math.absoluteValue
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    lateinit var queue: RequestQueue
    lateinit var button: Button
    lateinit var cityText:EditText

    lateinit var deg:TextView
    lateinit var wind:TextView
    lateinit var cityView:TextView
    lateinit var humidity:TextView
    lateinit var clouds:TextView
    lateinit var pressure:TextView
    lateinit var minTemp:TextView
    lateinit var maxTemp:TextView

    lateinit var progress:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initvars()
        var gson=Gson()


        button.setOnClickListener {
            var city = cityText.text.toString()
            val url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=5814201029ba001e12185ab57c6ddd8e"
            progress.visibility=ProgressBar.VISIBLE
            get(url,object : Data<JSONObject>{
                override fun Data(data: JSONObject?) {
                    if (data != null) {
                        if(data.get("done") as Boolean){
                            var response= gson.fromJson(data.get("content").toString(),ResponseData::class.java)
                            Log.d("check",data.toString())
                            progress.visibility=ProgressBar.INVISIBLE
                            deg.text= (response.main.temp-273).toInt().toString()+" C"
                            humidity.text=response.main.humidity.toString()+"%"
                            wind.text=response.wind.speed.toString()
                            cityView.text=response.name
                            clouds.text=response.clouds.all.toString()+"%"
                            pressure.text=response.main.pressure.toString()
                            maxTemp.text=(response.main.temp_max-273).toInt().toString()+" C"
                            minTemp.text=(response.main.temp_min-273).toInt().toString()+" C"
                        } else{
                            progress.visibility=ProgressBar.INVISIBLE
                            Toast.makeText(applicationContext,"Enter Valid city",Toast.LENGTH_LONG).show()
                            reset()
                        }
                    }
                }
            })
        }


    }

    fun initvars(){
        button = findViewById(R.id.button);
        cityText = findViewById(R.id.city);
        deg = findViewById(R.id.deg);
        wind = findViewById(R.id.wind);
        cityView = findViewById(R.id.cityview);
        humidity = findViewById(R.id.humidity);
        progress = findViewById(R.id.progress);
        clouds = findViewById(R.id.clouds);
        pressure = findViewById(R.id.pressure);
        minTemp = findViewById(R.id.min_temp);
        maxTemp = findViewById(R.id.max_temp);
        queue = Volley.newRequestQueue(applicationContext)
    }

    fun reset(){
        deg.text=""
        humidity.text=""
        wind.text=""
        cityView.text=""
        clouds.text=""
        pressure.text=""
        minTemp.text=""
        maxTemp.text=""
    }

    fun get(url:String,data:Data<JSONObject>){
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    var v=JSONObject()
                    v.put("done",true)
                    v.put("content",response)
                    data.Data(v)
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    var v=JSONObject()
                    v.put("done",false)
                    v.put("content",error)
                    data.Data(v)
                }
            }
        )
        queue.add((jsonObjectRequest))
    }
}