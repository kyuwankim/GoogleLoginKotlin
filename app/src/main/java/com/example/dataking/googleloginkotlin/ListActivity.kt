package com.example.dataking.googleloginkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.dataking.googleloginkotlin.Adapter.RecyclerAdapter
import com.example.dataking.googleloginkotlin.Model.User
import com.example.dataking.googleloginkotlin.Model.Video
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result


class ListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rv = findViewById<View>(R.id.recyclerview) as RecyclerView
        var video = listOf<Video>()

        val ra = RecyclerAdapter(
                video,
                R.layout.list_item,
                this
        )

        rv.adapter = ra
        rv.layoutManager = LinearLayoutManager(this)

        FuelManager.instance.basePath = getString(R.string.server_url)
        Fuel.get("/videos/all").responseObject(Video.ListDeserializer()) { _, _, result ->
            when (result) {
                is Result.Success -> {
                    video = result.get()
                    ra.data = video
                    ra.notifyDataSetChanged()

                }
                is Result.Failure -> {
                    Log.e("Video", "fail...")
                }
            }
        }

        Fuel.post("/auth/mobile/google").responseObject(User.Deserializer()) { _, _, result ->
            when (result) {
                is Result.Success -> {
                    Log.e("User", "" + result.get())
                }

                is Result.Failure -> {
                    Log.e("User", "Fail")
                }
            }
        }

    }
}



