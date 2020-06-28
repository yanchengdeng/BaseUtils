package deng.yc.baseutils.databind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import deng.yc.baseutils.R
import deng.yc.baseutils.databinding.ActivityDataBindBinding

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
//        binding.user.firstName = "hello world!"
    }
}