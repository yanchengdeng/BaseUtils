package deng.yc.baseutils.room

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 *@Author : yancheng
 *@Date : 2020/7/21
 *@Time : 16:30
 *@Describe ：
 **/


@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val mId : String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "username")
    val mUserName : String,
    @ColumnInfo(name = "last_update")
    val mDate : Date = Date(System.currentTimeMillis()),
    @ColumnInfo(name = "nick_name")
    val nickname: String
)