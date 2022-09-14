package com.example.arunalistapp.network

import com.example.arunalistapp.home.data.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    fun fetchAllPosts(): Call<List<PostModel>>


    @GET("posts")
    fun searchPosts(@Query("title") searchText: String): Call<List<PostModel>>

}