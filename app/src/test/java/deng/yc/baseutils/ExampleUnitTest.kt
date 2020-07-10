package deng.yc.baseutils

import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
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
    fun encode(){
        val password = "123456"
      val  passwordEncode =   EncodeUtils.base64Encode(password)

//        LogUtils.w("123456加密后 : $passwordEncode")
        System.out.println("123456加密后 : $passwordEncode")

        val passwordDecode = EncodeUtils.base64Decode(passwordEncode)

        System.out.println("123456解密后 : $passwordEncode")

        assertEquals("123456",passwordDecode)
    }
}
