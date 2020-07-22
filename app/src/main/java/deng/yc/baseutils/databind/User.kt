package deng.yc.baseutils.databind

/**
 *@Author : yancheng
 *@Date : 2020/6/1
 *@Time : 16:22
 *@Describe ：
 **/

data class User(var age: Int,var firstName: String, val lastName: String){
    companion object{
        const val MAX_AGE  = 14
        const val MIN_AGE = 10
    }
}


data class Contact(
    val id: String,
    val name: String,
    val email: String,
    val phone : String
)
