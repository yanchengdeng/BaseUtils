package deng.yc.baseutils

import android.Manifest
import android.app.Application
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 *@Author : yancheng
 *@Date : 2020/6/28
 *@Time : 14:16
 *@Describe ：
 **/

class MyApplication  :Application(){
    override fun onCreate() {
        super.onCreate()

        UMConfigure.init(this, "5ef83524978eea088379e7cc", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "String pushSecret");
        UMConfigure.sAppkey
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);


    }
}