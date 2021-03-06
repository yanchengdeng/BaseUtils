package deng.yc.baseutils

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.provider.Telephony
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils


/**
 *@Author : yancheng
 *@Date : 2020/7/8
 *@Time : 14:30
 *@Describe ：
 **/
class SmsContentObserver constructor(context:Context,handler:Handler) : ContentObserver(handler){
    private val smsInboxUri = "content://sms/inbox"
    private val ctx: Context = context
    private val sortOrder: String = Telephony.Sms.DATE+" desc"

    private var msg_id = "0"

    private var mUri: Uri? = null
//    private val address = "10001" // 发送短信地址


    /**
     * 双卡双待  调用两次
     */
    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        /**
         * // 第一遍 先执行 content://sms/raw
        // 第二遍则是 content://sms/inbox
        if (uri.toString().equals("content://sms/inbox")) {
        // return 后就不会执行发送数据到手环的代码了
        return;
        }
         */

        LogUtils.d( "dyc. onChange=" + selfChange + ", uri=" + uri)
        /**
         * 适配某些较旧的设备，可能只会触发onChange(boolean selfChange)方法，没有传回uri参数，
         * 此时只能通过"content://sms/inbox"来查询短信
         */
//        if (uri == null ) {
//            uri = Uri.parse("content://sms/inbox")
//        }

        /**
         * 06-15 11:45:48.706 D/SmsContent: onChange(boolean selfChange, Uri uri). selfChange=false, uri=content://sms/raw
         * 06-15 11:45:49.466 D/SmsContent: onChange(boolean selfChange, Uri uri). selfChange=false, uri=content://sms/387
         *
         * Generally onChange will be triggered twice, first time is triggered by uri "content://sms/raw"(sms received,
         * but have not written into inbox), second time is triggered by uri "content://sms/387"(number is sms id)
         */


        mUri = uri ?:  Uri.parse(smsInboxUri)

        if (mUri.toString().contains("content://sms/raw") || mUri.toString() == "content://sms") {
            return
        }


        val cursor = ctx?.contentResolver?.query(mUri!!,null,null,null,sortOrder)
        if (cursor!=null){
            if (cursor.moveToFirst()){
//                val person = cursor.getString(cursor.getColumnIndex(Telephony.Sms.PERSON))
                val _id = cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID))
                val phone = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS))
                val date = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE))
                val subject = cursor.getString(cursor.getColumnIndex(Telephony.Sms.SUBJECT))
                val smsBody = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY))
                    //status         状态 -1接收，0 complete, 64 pending, 128 failed
                val status = cursor.getString(cursor.getColumnIndex(Telephony.Sms.STATUS))

                /**
                 * type
                ALL    = 0;
                INBOX  = 1;
                SENT   = 2;
                DRAFT  = 3;
                OUTBOX = 4;
                FAILED = 5;
                QUEUED = 6;
                 */
                val type = cursor.getString(cursor.getColumnIndex(Telephony.Sms.TYPE))
                val dateString = TimeUtils.millis2String(date.toLong(),"yyyy-MM-dd HH:mm:ss")

                //persion  为空 有两种形式  0  null


                cursor.close()
                if (msg_id != _id){
                    msg_id = _id
                    LogUtils.d("dyc","   消息类型 =$type: _id = $_id  状态：$status  手机：$phone : 日期：$dateString : 标题 ：$subject : 消息体：$smsBody")
                }else{
                    LogUtils.d("dyc","重复数据msg_id = $msg_id")
                    return
                }
            }
        }


    }

//    override fun onChange(selfChange: Boolean) {
//
//        super.onChange(selfChange)
//        LogUtils.d("dyc","onChange 发生了")
//
//
//    }

//    override fun deliverSelfNotifications(): Boolean {
//        return true
//    }





}