package com.anji.plus.summerchenlibrary.utils.httputil;


import com.anji.plus.summerchenlibrary.utils.MD5Util;
import com.anji.plus.summerchenlibrary.utils.StringUtil;
import com.google.gson.Gson;

import java.util.TreeMap;


/**
 * @author hailong .
 *         Create on 2017/8/9
 */

public class PostData extends TreeMap {
    private TreeMap<String, String> map = new TreeMap<>();

    public PostData() {
        //根据不同的需求修改map组合
        put("time", String.valueOf(System.currentTimeMillis() / 1000));
//        String token = SharedPreferencesUtil.getInstance(BaseApplication.getInstance()).getValueByKeyString(SharePreferenceKey.TOKEN, "");
        String token ="";
        if (StringUtil.isEmpty(token)) {
            token = "test";
        }
        put("token", token);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 封装好的SignMap
     *
     * @return
     */
    public void post() {
        put("reqData", new TreeMap<>(map));
        put("sign", getSign());
    }


    /**
     * 传递参数
     *
     * @param key
     * @param value
     */
    public void push(Object key, Object value) {
        map.put(key.toString(), value.toString());
    }

    /**
     * 对Sign进行md5加密
     *
     * @return 获取签名
     */
    private String getSign() {

        String baseSignMsg = "reqData=" + new Gson().toJson(map) + "&time=" + String.valueOf(get("time"))
                + "&token=" + String.valueOf(get("token"));
        String sign = MD5Util.getMD5(baseSignMsg);

        return sign;
    }
}

