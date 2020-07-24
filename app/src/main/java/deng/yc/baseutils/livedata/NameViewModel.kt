package deng.yc.baseutils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import deng.yc.baseutils.room.User
import deng.yc.baseutils.room.UserDAO
import deng.yc.baseutils.room.UsersDatabase
import kotlin.Lazy as lazy

/**
 *@Author : yancheng
 *@Date : 2020/7/15
 *@Time : 16:02
 *@Describe ：
 **/

class NameViewModel(private val userDAO: UserDAO) : ViewModel(){

//    private val userDAO = dao

    //创建一个字符串类型的 LiveData
    val currentName :MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val user : MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }


    fun findUsers() : LiveData<List<User>>{
        return userDAO.getUsers()
    }

    fun  addUser(user: User){
        userDAO.insertUser(user)
    }


    fun  findUser(id :String) : LiveData<User>{
       return userDAO.getUserById(id)
    }
}