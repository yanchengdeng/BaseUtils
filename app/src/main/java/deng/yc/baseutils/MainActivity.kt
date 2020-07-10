package deng.yc.baseutils

import android.Manifest
import android.annotation.SuppressLint
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


    @SuppressLint("CheckResult")
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
            .request(Manifest.permission.READ_SMS,Manifest.permission.READ_CONTACTS)
            .subscribe { granted ->
                if (granted) {
                    ToastUtils.showShort("已授权")
                    getPhoneInfo()
                } else {
                    ToastUtils.showShort("请授权")
                }
            }



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


        testRsa()


    }

    private fun  getPhoneInfo(){
        QueryContactUtils.testContactNameByNumber(this)
    }


    private fun testRsa(){
        // 生成公钥和私钥
//        Test.genKeyPair()
        // 加密字符串
        // 加密字符串
        val message = "df723820"
        var messageEn =
            Test.encrypt(message, Test.publicKeyString)
        LogUtils.w("dyc","$message\t加密后的字符串为:$messageEn")

        messageEn = "aDkDcLYZXTRh9WwJfoziJl1S2KodS/08A+LybrNZUMEO8meLIdR1UgWFbnu7btv0WghJuM64BkNg97pCrqDv2Pq4n0Q/gSxYthMN+6O+U6bXxxF+Gtt8AKMzfPt8hvz8KJ/oFCZ/ZoUBLqsDucxCjvB9rAgf3lh7Jf9O+Kv+Tcs="

        messageEn = "TOidpgZbr32AQLM2jRLJDeAGdj8gLPWMjT/HMc0/jXIS4Ge38o/1rnrVHqK5uTJEX8owyM6KkkCuR16uXySRc1chmzDusPQCVjEXBRohYL7SNsaHFsqsocyzspAcI7dwUg1QgtvtfH9UDZvSaPPRNwyPWIng5FCIDg+/ncO+d2k="
        val messageDe =
            Test.decrypt(messageEn, Test.privateKeyString)
        LogUtils.w("dyc","还原后的字符串为:$messageDe")
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

    override fun onBackPressed() {
       doubleClickExist()
    }

    private var mExitTime: Long = 0

    /****
     * 连续两次点击退出
     */
    private fun doubleClickExist(): Boolean {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showShort(R.string.double_click_exit)
            mExitTime = System.currentTimeMillis()
            return true
        } else {
            ActivityUtils.finishAllActivities()
        }
        return false
    }


}

