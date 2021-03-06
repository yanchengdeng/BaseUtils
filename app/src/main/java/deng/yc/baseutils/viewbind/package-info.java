/***
 *
 * 使用说明
 * 1. 需要咋 builder.gradle 中的添加
 * android {
 *         ...
 *         viewBinding {
 *             enabled = true
 *         }
 *     }
 *
 *
 * 2.如果要忽略不生成布局绑定 可以 在布局文件中添加
 * <LinearLayout
 *             ...
 *             tools:viewBindingIgnore="true" >
 *         ...
 *     </LinearLayout>
 *
 *
 * 3.比如 创建以为xml 文件 为：activity_view_bind.xml
 *
 * 则  系统会生成 ActivityViewBindBinding  绑定视图
 * 剩下详见 deng.yc.baseutils.viewbind.ViewBindActivity
 *
 *
 **/
package deng.yc.baseutils.viewbind;