package deng.yc.baseutils.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@Author : yancheng
 *@Date : 2020/7/23
 *@Time : 17:03
 *@Describe ：
 **/

const val IMAGE_URL ="https://t8.baidu.com/it/u=1484500186,1503043093&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1596102714&t=8fb527082b55741352d9d1d6019c4893"

class UploadWorker (private val appContext: Context,workerParameters: WorkerParameters): Worker(appContext,workerParameters){
    override  fun doWork(): Result {

        GlobalScope.launch {
           uplaodImage()
        }

      return  Result.success()

    }

    /**
     *
    已成功完成：Result.success()
    已失败：Result.failure()
    需要稍后重试：Result.retry()

     */
    @SuppressLint("CheckResult")
    suspend fun uplaodImage() =  coroutineScope {
        runBlocking {
            ToastUtils.showShort("开始下载")
            Glide.with(appContext).load(IMAGE_URL).addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    print(e?.message)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    print("onResourceReady---${resource}")
                    return true
                }
            }).submit()
        }
        return@coroutineScope
        }

}