package com.example.dataking.googleloginkotlin.Model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by dataking on 2017-12-14.
 */
data class User(
        val _id: String,
        val email: String,
        val name: String,
        val picture: String,
        val videos: List<Video>,
        val videoLiked: List<Video>,
        val following: List<Video>,
        val followers: List<Video>,
        val route: List<Video>,
        val registration: String,
        val birthday: String,
        val gender: String,
        val password: String
) {
    class Deserializer : ResponseDeserializable<List<User>> {
        override fun deserialize(content: String): List<User>? {

            val type = object : TypeToken<List<User>>() {}.type
            return Gson().fromJson(content, type)
        }

        class ListDeserializer : ResponseDeserializable<List<User>> {
            override fun deserialize(content: String): List<User>? {

                val type = object : TypeToken<List<Video>>() {}.type
                return Gson().fromJson(content, type)
            }
        }
    }

}