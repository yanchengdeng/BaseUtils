package deng.yc.baseutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.MainThread
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.umeng.analytics.AnalyticsConfig
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.utils.UMUtils
import deng.yc.baseutils.databind.DataBindActivity
import deng.yc.baseutils.databinding.ActivityDataBindBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var URL = "https://xueqiu.com/"
    }


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







        webview.loadUrl(DataUrl.datas[channel])




        webview.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
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
}
