package deng.yc.baseutils.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.Lazy as lazy

/**
 *@Author : yancheng
 *@Date : 2020/7/15
 *@Time : 16:02
 *@Describe ：
 **/

class NameViewModel : ViewModel(){


    //创建一个字符串类型的 LiveData
    val currentName :MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}