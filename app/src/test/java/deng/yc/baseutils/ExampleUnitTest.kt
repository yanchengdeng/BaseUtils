package deng.yc.baseutils

import androidx.lifecycle.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    internal class NameTips {
        var firstName: String? = null
        var lastName: String? = null

    }


    @Test
    fun  testTransfromation(){
        val users: LiveData<NameTips?> = MutableLiveData<NameTips>()


        val stringLiveData = Transformations.map(
            users
        ) { user -> user?.firstName + user?.lastName }

        println(stringLiveData)


    }

}
