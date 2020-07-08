package deng.yc.baseutils

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.utils.UMUtils
import deng.yc.baseutils.databind.DataBindActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var URL = "https://xueqiu.com/"

        val SMS = "content://sms"
    }



    private var smsContentObserver: SmsContentObserver? = null
    private val myHandler : Handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            LogUtils.w("dyc handler:"+msg)
            return false
        }

    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        URL = "https://xueqiu.com/"
        bind_data.setOnClickListener {

            ActivityUtils.startActivity(DataBindActivity::class.java)
        }



        thread_annotation.setOnClickListener {
            testThread()
        }


        var channel = ChannelUtil.getChannel(this, "未知")

        bind_data.text = channel
        UMUtils.setChannel(this,channel)

        val permissions = RxPermissions(this)
        permissions
            .request(Manifest.permission.READ_SMS)
            .subscribe({ granted ->
                if (granted) {
                    ToastUtils.showShort("已授权")
                } else {
                   ToastUtils.showShort("请授权")
                }
            })







        webview.loadUrl(DataUrl.datas[channel])




        webview.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        smsContentObserver = SmsContentObserver(this,handler = myHandler)
        smsContentObserver?.let {
            contentResolver.registerContentObserver(Uri.parse(SMS),true,it)
            ToastUtils.showShort("启动")
        }

    }



    @MainThread
    private fun testThread(){

        println(" dyc ${Thread.currentThread()} 的id : ${Thread.currentThread().id}")
        println("dyc ${Thread.currentThread()} 的名称： ${Thread.currentThread().name}")
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this); // 不能遗漏

    }

    override fun onStop() {
        super.onStop()
        MobclickAgent.onPause(this); // 不能遗漏

    }

    override fun onDestroy() {
        super.onDestroy()
        smsContentObserver?.let {
            contentResolver.unregisterContentObserver(it)
            ToastUtils.showShort("关闭啦。。。")
        }
    }
}

