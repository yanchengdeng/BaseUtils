package deng.yc.baseutils.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import deng.yc.baseutils.DataUrl.Companion.datas
import deng.yc.baseutils.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *  Todo
 *  继续研究下  协程并没跟随activity 销毁而 销毁
 */
class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)


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
    }

}