package deng.yc.baseutils.kolin

/**
 *@Author : yancheng
 *@Date : 2020/7/14
 *@Time : 14:38
 *@Describe
 *
 *
 * sealed 类似枚举
 * sealed class的子类必须声明在同一个文件中。
 *
 **/


sealed class Expr
data class Const1(val number: Double) : Expr()
data class Sum1(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const1 -> expr.number
    is Sum1 -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

fun eval1(expr: Expr){
    when(expr){

    }
}