package deng.yc.baseutils.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import deng.yc.baseutils.R
import deng.yc.baseutils.Response
import deng.yc.baseutils.service
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import java.util.*

/**
 *
 *  继续研究下  协程并没跟随activity 销毁而 销毁
 */
class CoroutineActivity : AppCompatActivity() {


    private lateinit var  moviesViewModel :MovieViewModel

    lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)


        job = Job()

        moviesViewModel = MovieViewModel()

        moviesViewModel.movieTypes.observe(this, androidx.lifecycle.Observer {

            textView.text = it.data.toString()
        })


        /**
         * runBlocking
         * 创建了一个协程，并且会阻塞当前线程，等待作用域也就是{}内的代码以及所有子协程结束
         * 特点：1.有返回值 2. 生命周期  3.阻塞线程
         */

        run_blocking.setOnClickListener {

            val runblock  =  runBlocking {
                loadDataFromUi()
                printlnEnd(this.toString())

            }
            println("run_blocking  end  ${runblock }}")
        }



        /**
         * 这个方法创建出来的协程是一个顶层的协程，它的生命周期跟application是一样的
         * 特点：1 无返回值  2.不可取消  3.无阻塞  4.声明周期长
         */
        globalscope_launch.setOnClickListener {
            GlobalScope.launch {
                loadDataFromUi()

                printlnEnd(this.toString())
            }

            println("GlobalScope  end")
        }



        job_launch.setOnClickListener {
            runBlocking {
            val job = GlobalScope.launch {
                delay(5000)
                println("kotlin....coroutines")
            }

            println("hello... ")
            job.join()
             println("world....")

            }
        }

        job_launch_life.setOnClickListener {

            CoroutineScope(job).launch {
                val job = GlobalScope.launch {
                    println("start---${Date()}")
                 val result =    getData()
                    if (result.errorCode==0){
                        println(result.data)
                    }
                    println("end--${Date()}")
                    println("kotlin....coroutines---job")
                }

                println("hello...job ")
                job.join()
                println("world....job")

            }
        }


        viewmodle_life.setOnClickListener {
            GlobalScope.launch{
                moviesViewModel.getMovieTypes()
            }
        }
    }


    private suspend fun getData() :Response{

//        delay(5000)

      return service.listRepos()

    }



    private fun loadDataFromUi() {
        val datas = StringBuilder()
        for (i in 1..10){
            Thread.sleep(1000)
            println(datas.append("$i --------"))
        }
        println("最终数据：${datas}")
    }


    private fun printlnEnd(name :String){
        println("${name }方法结束了")
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}