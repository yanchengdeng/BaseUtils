package deng.yc.baseutils.room;

/**
*@author : yanc -> 大道之行
*@Create : 2020/7/21
*@Time : 16:24
*@Describe ：
 *
 * 1.添加引用资源
 *   implementation "android.arch.persistence.room:runtime:$rootProject.roomVersion"
 *     annotationProcessor "android.arch.persistence.room:compiler:$rootProject.roomVersion"
 *     androidTestImplementation "android.arch.persistence.room:testing:$rootProject.roomVersion"
 *
 *
 *     2.为了迁移到Room，我们需要增加数据库版本，为了保存用户数据，我们需要实现一个迁移类。为了测试迁移，我们需要导出模式
 *     在app/build.gradle  添加
 *      // used by Room, to test migrations
 *         javaCompileOptions {
 *             annotationProcessorOptions {
 *                 arguments = ["room.schemaLocation":
 *                                      "$projectDir/schemas".toString()]
 *             }
 *         }
 *     }
 *
 *
 *     // used by Room, to test migrations
 *     sourceSets {
 *         androidTest.assets.srcDirs +=
 *                 files("$projectDir/schemas".toString())
 *     }
 *
 *
 *
 * 3.创建库数据
 * @Entity(tableName = "users")
 * data class User(
 *     @PrimaryKey
 *     @ColumnInfo(name = "userid")
 *     val mId : String = UUID.randomUUID().toString(),
 *     @ColumnInfo(name = "username")
 *     val mUserName : String,
 *     @ColumnInfo(name = "last_update")
 *     val mDate : Date = Date(System.currentTimeMillis())
 * )
 *
 * 4. Create Data Access Objects (DAOs)  创建数据访问对象
 *  @Dao
 * interface UserDAO {
 *
 *     @Query("SELECT * FROM Users")
 *     fun getUsers():List<User>
 * }
 *
 * 5.创建数据库
 *
 *
**/