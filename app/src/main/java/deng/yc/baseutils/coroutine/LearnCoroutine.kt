package deng.yc.baseutils.coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.Serializable

/**
 *@Author : yancheng
 *@Date : 2020/7/17
 *@Time : 14:46
 *@Describe ：
 **/

suspend fun loadAndCombine(strs: MutableList<CoroutineData>) : String =
    coroutineScope {
        val value1 = async { loadImage(strs[0]) }
        val value2 = async { loadImage(strs[1]) }
        val value3 = async { loadImage(strs[2]) }
        // 可以确保所有异步完成后，才继续往下走    整个coroutineScope中的操作将会被取消
        return@coroutineScope combineImage(value1.await(),value2.await(),value3.await())
    }

fun combineImage(
    value1: CoroutineData,
    value2: CoroutineData,
    value3: CoroutineData
): String {
    println(value1)
    println(value2)
    println(value3)
        return  ""
}


fun loadImage(value: CoroutineData): CoroutineData {
    Thread.sleep(value.time)
    return value
}


/**
 * coroutine的管理
 */
 suspend fun main() {
    val coroutineDatas = listOf(CoroutineData(1000,"方法1"),CoroutineData(3000,"方法1"),CoroutineData(1500,"方法3"))


    loadAndCombine( coroutineDatas.toMutableList())

}


