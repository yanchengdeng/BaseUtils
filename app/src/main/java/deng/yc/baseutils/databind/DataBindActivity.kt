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
class DataBindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_data_bind)

        binding.user = User(18,"Test","User")



        btn_click.setOnClickListener {
            binding.user = User((Math.random()*15).toInt(),"哈哈哈哈","哈哈哈哈lastalast")
        }
    }
}