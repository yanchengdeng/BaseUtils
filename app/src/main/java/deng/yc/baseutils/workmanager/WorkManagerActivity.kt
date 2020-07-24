package deng.yc.baseutils.workmanager

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import deng.yc.baseutils.R
import kotlinx.android.synthetic.main.activity_work_manager.*
import org.koin.androidx.scope.lifecycleScope
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val stringbuild = StringBuilder()

        // Create a Constraints object that defines when the task should run
        val constraints = Constraints.Builder()
            .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .build()


        val uploadWorker = OneTimeWorkRequestBuilder<UploadWorker>()
//            .setConstraints(constraints)
//            .setInitialDelay(10,TimeUnit.SECONDS)
            .build()


        //观察工作状态
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorker.id)
            .observe(this, Observer { workInfo ->
//                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val progress = workInfo.progress
                    val value = progress.getInt("Progress", 0)

                val datas =     stringbuild.append(workInfo.state).append(workInfo.progress.keyValueMap.toString()).append("\n")

                    textView2.text = datas.toString()

//                }


            })


        //一定要进任务放到后台队列中
        WorkManager.getInstance(this).enqueue(uploadWorker)




    }
}