package deng.yc.baseutils.coroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import deng.yc.baseutils.Response
import deng.yc.baseutils.ResponseItem
import deng.yc.baseutils.service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *@Author : yancheng
 *@Date : 2020/7/28
 *@Time : 17:50
 *@Describe ：
 **/

class MovieViewModel  : ViewModel(){

     val movieTypes = MutableLiveData<Response>()


     suspend fun getMovieTypes() = withContext(Dispatchers.Default){
         movieTypes.postValue( service.listRepos())
    }



}