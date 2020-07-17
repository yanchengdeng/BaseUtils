package deng.yc.baseutils.coroutine

import java.io.Serializable

/**
 *@Author : yancheng
 *@Date : 2020/7/17
 *@Time : 15:13
 *@Describe ：
 **/


data class CoroutineData(
    var time : Long,
    var name : String
): Serializable