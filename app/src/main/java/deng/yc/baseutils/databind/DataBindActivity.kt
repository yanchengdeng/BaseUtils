package deng.yc.baseutils.databind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ThreadUtils
import deng.yc.baseutils.R
import deng.yc.baseutils.databinding.ActivityDataBindBinding
import kotlinx.android.synthetic.main.activity_data_bind.*
import kotlin.random.Random

/**
*@author : yanc -> 大道之行
*@Create : 2020/6/1
*@Time : 16:24
*@Describe ：数据绑定
**/
class DataBindActivity : BaseDataBindActivity<ActivityDataBindBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = DataBindingUtil.setContentView<ActivityDataBindBinding>(
//            this, R.layout.activity_data_bind)

        ( dataBind as ActivityDataBindBinding).user = User(18,"Test","User")
        dataBind?.lifecycleOwner = this


        btn_click.setOnClickListener {
            ( dataBind as ActivityDataBindBinding).user = User((Math.random()*15).toInt(),"哈哈哈哈","哈哈哈哈lastalast")
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_data_bind
    }
}