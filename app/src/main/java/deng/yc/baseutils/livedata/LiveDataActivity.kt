package deng.yc.baseutils.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import deng.yc.baseutils.R
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlin.random.Random

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
    private val model : NameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val nameObserver = Observer<String>{
            tv_name.text = it.toString()

        }

        model.currentName.observe(this,nameObserver)



        btn_click.setOnClickListener {
            val datas = (Math.random()*1000).toInt().toString()
            model.currentName.postValue(datas )

            tv_contents.text = model.currentName.value
        }
    }


}