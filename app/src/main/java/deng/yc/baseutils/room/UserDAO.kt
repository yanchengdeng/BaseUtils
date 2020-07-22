package deng.yc.baseutils.room

import androidx.room.*

/**
 *@Author : yancheng
 *@Date : 2020/7/21
 *@Time : 16:36
 *@Describe ：
 **/


@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getUsers():List<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertUser(user :User)


    @Query("select * from users where username = :name")
    fun getUserById(name: String): List<User>


    @Query("DELETE  FROM users")
    fun deleteAll()


}