package deng.yc.baseutils

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *@Author : yancheng
 *@Date : 2020/7/28
 *@Time : 17:24
 *@Describe ：
 **/


var retrofit: Retrofit? = Retrofit.Builder()
    .baseUrl("https://www.wanandroid.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var service: GitHubService = retrofit?.create(GitHubService::class.java)!!


data class Response(val data :List<ResponseItem> , val errorCode:Int,val errorMsg : String)

data class  ResponseItem( val  id :Int,
   val name :String
)

interface GitHubService {
    @GET("hotkey/json")
   suspend fun listRepos(): Response
}