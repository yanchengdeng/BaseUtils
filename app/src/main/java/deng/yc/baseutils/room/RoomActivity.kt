package deng.yc.baseutils.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import deng.yc.baseutils.R
import kotlinx.android.synthetic.main.activity_room.*
import java.util.*

class RoomActivity : AppCompatActivity() {
    private lateinit var dao: UserDAO

    //    lateinit var dao: UserDAO_Impl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        dao = UsersDatabase.getInstance(this).userDao()

        add_record.setOnClickListener {
            //添加一条数据
            dao.insertUser(User(mUserName = (Math.random() * 1000).toInt().toString(),nickname = "昵称${(Math.random() * 1000).toInt()}"))




            setTextContents()
        }

        find_record.setOnClickListener {

            if (et_name.text.toString().isEmpty()) {
                ToastUtils.showShort("输入名称")
                return@setOnClickListener
            }
            //添加一条数据
            val listDatas = dao.getUserById(et_name.editableText.toString())

            tv_contents.text = listDatas.value?.toString()

        }

        delete_all.setOnClickListener {

            dao.deleteAll()

            setTextContents()


        }


    }

    private fun setTextContents() {
        val listDatas = dao.getUsers()
        //fixme  这里的  liveData 。value 无数据
        tv_contents.text = listDatas.value?.joinToString { "用户信息:\n $it \n" }
    }
}