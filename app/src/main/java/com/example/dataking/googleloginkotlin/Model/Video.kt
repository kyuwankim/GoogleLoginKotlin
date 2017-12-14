package com.example.dataking.googleloginkotlin.Model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

/**
 * Created by dataking on 2017-12-14.
 */
data class Video(val _id: String,
                 val title: String,
                 val youtubeId: String,
                 val category: String,
                 val latitude: Float,
                 val longitude: Float,
                 val address: String,
                 val user: User
     ) {
    class ListDeserializer : ResponseDeserializable<List<Video>> {
        override fun deserialize(content: String): List<Video>? {

            val type = object : TypeToken<List<Video>>() {}.type
            return Gson().fromJson(content, type)
        }
    }
}

