package com.anji.plus.summerchenlibrary.utils.httputil;


import com.anji.plus.summerchenlibrary.utils.LogUtil;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by SummerChen on 2018/1/15.
 */

public class MyHttpUtil {
    /**
     * get请求
     *
     * @param url
     * @param callBack
     */
    public static void myHttpGet(String url, MyNetCallBack callBack) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        SignOkHttpClient.getInstance().newCall(request).enqueue(callBack);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public static void myHttpPost(String url, Map<String, String> params, MyNetCallBack callBack) {
        LogUtil.i("MyHttpUtil", "请求参数：" + params.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(params));
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        SignOkHttpClient.getInstance().newCall(request).enqueue(callBack);

    }

}
