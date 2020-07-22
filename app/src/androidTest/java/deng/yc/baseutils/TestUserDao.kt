package deng.yc.baseutils

import android.app.Instrumentation
import androidx.room.Database
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import deng.yc.baseutils.room.User
import deng.yc.baseutils.room.UsersDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 *@Author : yancheng
 *@Date : 2020/7/21
 *@Time : 16:55
 *@Describe ：
 **/

class TestUserDao {

     private var mDatabase: UsersDatabase? = null
    @Before
    fun initDb(){
        mDatabase = Room.databaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,UsersDatabase::class.java,"user").build()
    }


    @Test
    fun insertAndGetUser(){
        val user = User(mUserName = "严惩不贷")
        mDatabase?.userDao()?.insertUser(user)


        val users = mDatabase?.userDao()?.getUsers()

        assertEquals(users?.size,1)
    }

    /**
     *
     */
    @After
    fun closeDb(){
        mDatabase?.close()
    }

}
