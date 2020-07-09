package deng.yc.baseutils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.blankj.utilcode.util.LogUtils;

import deng.yc.baseutils.databind.Contact;

public class QueryContactUtils {
 
    // 查询联系人的业务方法
    public static List<Contact> queryContact(Context context) {
        // [0]创建集合
        List<Contact> contactLists = new ArrayList<Contact>();
 
        // [1]由于联系人的数据库系统已经通过内容提供者暴露出来 所以我们可以直接通过内容解析者操作数据库
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri datUri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = context.getContentResolver().query(uri,
                new String[] { "contact_id" }, null, null, null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(0);

            String email = "",phone = "",name = "";
            if (contact_id != null) {

                System.out.println("contact_id:" + contact_id);
//                contact.id(contact_id);
                // [2]在查询data表 的data1列和mimetype_id列
                Cursor dataCursor = context.getContentResolver().query(datUri,
                        new String[] { "data1", "mimetype" },
                        "raw_contact_id=?", new String[] { contact_id }, null);
                while (dataCursor.moveToNext()) {
                    String data = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                    if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                        System.out.println("邮箱:" + data);
                        email = data;
//                        contact.setEmail(data);
 
                    } else if ("vnd.android.cursor.item/phone_v2"
                            .equals(mimetype)) {
                        System.out.println("电话:" + data);
//                        contact.setPhone(data);
                        phone = data;
 
                    } else if ("vnd.android.cursor.item/name".equals(mimetype)) {
                        System.out.println("姓名:" + data);
//                        contact.setName(data);
                        name = data;
                    }
                }
                // 把bean对象加入到集合
                Contact contact = new Contact(contact_id,name,email,phone);
                LogUtils.d("dyc",contact.toString());
               contactLists.add(contact);
            }
        }
        return contactLists;
    }


    //查询指定电话的联系人姓名，邮箱
    public static void testContactNameByNumber(Context context) throws Exception {
        String number = "13506976940";
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            LogUtils.d("dyc","名字:"+name);
        }
        cursor.close();
    }
}