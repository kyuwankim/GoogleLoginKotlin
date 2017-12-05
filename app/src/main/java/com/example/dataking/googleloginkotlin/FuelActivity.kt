package com.example.dataking.googleloginkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

class FuelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel)

        //if we set baseURL beforehand, simply use relativePath
        FuelManager.instance.basePath = "http://httpbin.org"
        "/get".httpGet().responseString { request, response, result ->
            //make a GET to http://httpbin.org/get and do something with response
            val (data, error) = result
            if (error == null) {
                //do something when success
                Log.d("success",""+data)
            } else {
                //error handling
                Log.d("fail",""+error)
            }
        }


    }
}
