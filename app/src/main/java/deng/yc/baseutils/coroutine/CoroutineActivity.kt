package deng.yc.baseutils.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import deng.yc.baseutils.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *
 */
class CoroutineActivity : AppCompatActivity(),CoroutineScope {
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        job = Job()

        loadDataFromUi()
    }



    fun loadDataFromUi() = launch {
        val ioData = async (Dispatchers.IO){
            //操作IO
            val datas = StringBuilder()
            for (i in 1..100000){
                Thread.sleep(500)
                println(datas.append("$i --------"))
            }
        }

        val data = ioData.await()

        println(data)
    }




    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}