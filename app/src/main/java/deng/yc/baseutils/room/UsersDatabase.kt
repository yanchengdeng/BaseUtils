package deng.yc.baseutils.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.blankj.utilcode.util.LogUtils

/**
 *@Author : yancheng
 *@Date : 2020/7/21
 *@Time : 16:38
 *@Describe ：
 **/

//entities表示把这几张表放入数据库
@Database(entities = [User::class], version = 3)
//表示在这个数据库中使用类型转换器
@TypeConverters(DateConverter::class)
abstract class UsersDatabase : RoomDatabase() {


    abstract fun userDao(): UserDAO


    companion object {
        private var INSTANCE: UsersDatabase? = null

        private val Migration_MEIGERATION_1_2 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //比如我从版本2  升级到版本3 user 表中添加了 一个 nick_name字段
                database.execSQL("ALTER TABLE users ADD nick_name TEXT Default 'null' not null")

            }
        }

        @JvmStatic
        fun getInstance(context: Context): UsersDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    UsersDatabase::class.java,
                    "user"
                ).addMigrations(Migration_MEIGERATION_1_2).allowMainThreadQueries().build()
            }
            return INSTANCE as UsersDatabase
        }
    }


}