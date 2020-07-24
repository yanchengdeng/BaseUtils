package deng.yc.baseutils

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Base64
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.utils.UMUtils
import deng.yc.baseutils.coroutine.CoroutineActivity
import deng.yc.baseutils.databind.DataBindActivity
import deng.yc.baseutils.livedata.LiveDataActivity
import deng.yc.baseutils.room.RoomActivity
import deng.yc.baseutils.viewbind.ViewBindActivity
import deng.yc.baseutils.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


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


        play_mp3.setOnClickListener {

            var player = MediaPlayer()
            val assetManager: AssetManager = assets
            try {
                val fileDescriptor =
                    assetManager.openFd("mongo.mp3")
                player.setDataSource(
                    fileDescriptor.fileDescriptor,
                    fileDescriptor.startOffset,
                    fileDescriptor.length
                )
                player.prepare()
                player.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


        view_bind.setOnClickListener {
            ActivityUtils.startActivity(ViewBindActivity::class.java)
        }


        ui_action.setOnClickListener {
            ActivityUtils.startActivity(WelcomeActivity::class.java)
        }



        live_data.setOnClickListener {
            ActivityUtils.startActivity(LiveDataActivity::class.java)
        }


        immersion.setOnClickListener {
            ActivityUtils.startActivity(TransTopActivity::class.java)
        }


        corouter.setOnClickListener {
            ActivityUtils.startActivity(CoroutineActivity::class.java)
        }


        room.setOnClickListener {
            ActivityUtils.startActivity(RoomActivity::class.java)
        }

        workmanager.setOnClickListener {
            ActivityUtils.startActivity(WorkManagerActivity::class.java)
        }




        var channel = ChannelUtil.getChannel(this, "未知")

//        bind_data.text = channel
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


        smsContentObserver = SmsContentObserver(this,handler = myHandler)
        smsContentObserver?.let {
            contentResolver.registerContentObserver(Uri.parse(SMS),true,it)
            ToastUtils.showShort("启动")
        }





    }

    private fun testRsa1() {

        val rsa = Rsa()
        var str = "我要加密这段文字。"
        println("原文:" + "我要加密这段文字。")
        var crypt = rsa.encryptByPrivateKey(str)
        println("私钥加密密文:$crypt")
        var result = rsa.decryptByPublicKey(crypt)
        println("原文:$result")

        println("---")

        str = "我要加密这段文字。"
        println("原文:" + "我要加密这段文字。")
        crypt = rsa.encryptByPublicKey(str)

        val base64Strencode = String(Base64.encode(crypt.toByteArray(), Base64.DEFAULT))

        println("公钥加密密文base64加密  :$base64Strencode")
        val base64Strdecode =  Base64.decode(base64Strencode, Base64.DEFAULT)
        println("公钥加密密文再base64解密  :${String(base64Strdecode)}")
        println("公钥加密密文:$crypt")
        result = rsa.decryptByPrivateKey(String(base64Strdecode))
        println("原文:$result")

        println("---")

        str = "我要签名这段文字。"
        println("原文：$str")
        val str1 = rsa.signByPrivateKey(str)
        println("签名结果：$str1")
        if (rsa.verifyByPublicKey(str1, str)) {
            println("成功")
        } else {
            println("失败")
        }
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



        val base64En = Test.base64Encode(messageEn)
        LogUtils.w("dyc","base64加密后："+base64En)
        val base64DE = Test.base64decode(base64En)
        LogUtils.w("dyc","base64解密后密后："+base64DE)
        val messageDe =
            Test.decrypt(messageEn, Test.privateKeyString)
        LogUtils.w("dyc","还原后的字符串为:$messageDe")
    }


    @MainThread
    private fun testThread(){

        println(" dyc ${Thread.currentThread()} 的id : ${Thread.currentThread().id}")
        println("dyc ${Thread.currentThread()} 的名称： ${Thread.currentThread().name}")

        (Thread.currentThread().isAlive)

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

