package com.anji.plus.summerchenlibrary.utils.baseapp;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SummerChen on 2018/4/12.
 */

public class BaseApplication extends Application {
    public static List<Object> activitys = new ArrayList<Object>();

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity))
            activitys.add(activity);
    }

    // 遍历所有Activity并finish
    public void destory() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
//        System.exit(0);
    }
}
