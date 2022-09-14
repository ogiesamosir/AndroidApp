package com.example.arunalistapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.arunalistapp.home.data.HomeRepository
import com.example.arunalistapp.home.data.PostModel
import com.example.arunalistapp.network.ApiClient
import com.example.arunalistapp.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository: HomeRepository?=null
    private var apiInterface: ApiInterface?=null

    var postModelListLiveData : LiveData<List<PostModel>>?=null

    init {
        homeRepository = HomeRepository()
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }

    fun SearchData(toString: String) {
//        postModelListLiveData = homeRepository?.searchPostData(toString)
        val data = MutableLiveData<List<PostModel>>()

        val call = apiInterface?.searchPosts(toString)
        call?.enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                val res = response.body()
                if (response.isSuccessful){
                    data.value = res
                }else{
                    data.value = null
                }
            }
            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                data.value = null
            }

        })
    }


}