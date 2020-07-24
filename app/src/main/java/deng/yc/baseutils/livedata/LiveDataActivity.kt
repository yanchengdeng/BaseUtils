package deng.yc.baseutils.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import deng.yc.baseutils.R
import deng.yc.baseutils.room.User
import deng.yc.baseutils.room.UsersDatabase
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.coroutines.launch

/**
 *
 * 如果观察者（由 Observer 类表示）的生命周期处于 STARTED 或 RESUMED 状态，
 * 则 LiveData 会认为该观察者处于活跃状态。LiveData 只会将更新通知给活跃的观察者。为观察 LiveData 对象而注册的非活跃观察者不会收到更改通知。
 *
 * LiveData 仅在数据发生更改时才发送更新，并且仅发送给活跃观察者。此行为的一种例外情况是，
 * 观察者从非活跃状态更改为活跃状态时也会收到更新。此外，如果观察者第二次从非活跃状态更改为活跃状态，
 * 则只有在自上次变为活跃状态以来值发生了更改时，它才会收到更新
 *
 * 使用步骤：
 * 1。创建ViewModel (LiveData 数据通常都存在ViewModel对象中)
 * 2.
 *
 */
class LiveDataActivity : AppCompatActivity() {

    // from the activity-ktx artifact
    private lateinit var model : NameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)


        lifecycleScope.launch {

        }

        model =  NameViewModel(UsersDatabase.getInstance(this).userDao())


        model.currentName.observe(this,Observer {
            tv_name.text = it.toString()

        })


        model.findUsers().observe(this,Observer<List<User>> {
            tv_contents.text = it.joinToString { item ->"用户信息 \n $item \n"  }
        })



        Transformations.map(model.findUsers()){
            user -> "${user.get(0)}"
        }

        val modles =model.findUser("23")


        val stringLiveData = Transformations.map(
            modles
        ) { user: User -> user.mUserName + user.nickname }

        btn_click.setOnClickListener {
            val datas = (Math.random()*1000).toInt().toString()
            //
//            model.currentName.postValue(datas )
            /***
             * 您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。
             * 如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象。
             */
            model.currentName.value = "你好世界"

            Thread.currentThread().name

//            tv_contents.text = model.currentName.value
        }

        bnt_add_user.setOnClickListener {
            model.addUser(User(mUserName = "一个小孩",nickname = "心儿"))
        }





        //测试   Transformations
        val  strLivedata= MutableLiveData<String>()

        strLivedata.value = "hha fs"

        val addStr : LiveData<String> =Transformations.map(strLivedata, ::yourFun)





        val listDatas = listOf("A","B","C","d","f")
      val mapsDatas =   listDatas.map {
            it.toUpperCase()
        }

        /**
         * 双引号 表示 直接可以引用一个function 作为参数
         */
        println(listDatas.mapIndexedNotNull(::mapindexFuction))



        println(listDatas)
        println(mapsDatas)


    }


    private fun mapindexFuction(index :Int,str: String) : String{
        return "$index + $str"
    }

    private fun yourFun(str:String)="新${str}被添加到数据库中"







}