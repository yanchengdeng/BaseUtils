package deng.yc.baseutils

import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import deng.yc.baseutils.kolin.StaticClass
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_static_kotlin(){
        StaticClass.doWork()
        StaticClass.doWork1()
    }
}
