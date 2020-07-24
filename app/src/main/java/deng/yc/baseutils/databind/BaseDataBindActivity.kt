package deng.yc.baseutils.databind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindActivity<T : ViewDataBinding?> : AppCompatActivity() {

    var dataBind : T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBind =   DataBindingUtil.setContentView<T>(this,getLayout())

    }

    abstract fun getLayout(): Int
}