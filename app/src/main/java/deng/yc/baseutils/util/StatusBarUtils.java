package deng.yc.baseutils.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;

import java.lang.reflect.Field;

public class StatusBarUtils {

    public static void setWindowStatusBarColor(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(activity.getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {}
        }
    }

}
