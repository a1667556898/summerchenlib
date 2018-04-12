package com.anji.plus.summerchenlibrary.utils.httputil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.anji.plus.summerchenlibrary.utils.LogUtil;
import com.anji.plus.summerchenlibrary.utils.NetUtil;
import com.anji.plus.summerchenlibrary.utils.ToastUtil;
import com.anji.plus.summerchenlibrary.utils.customview.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by SummerChen on 2018/1/15.
 */

public abstract class MyNetCallBack implements Callback {
    private Context context;
    private LoadingDialog loadingDialog;
    private String mMsg;
    public static final int SUCCESS = 0;
    public static final int TOKENFAIL = 1;
    public static final int FAIL = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    MyOnSuccess(msg.getData().getString("date"),mMsg);
                    break;
                case FAIL:
                    MyOnFailure(mMsg);
                    break;
                case TOKENFAIL:
                    MyOnFailure(mMsg);
                    ToastUtil.getShortToast((Activity)context,mMsg,1000);
//                    SharedPreferencesUtil.getInstance(context).putObject(SharePreferenceKey.USERINFO,null);
//                    ActivityManage.goToLoginActivity(context);
                    break;
            }
        }
    };

    public MyNetCallBack(Context context) {

        this.context = context;
        loadingDialog = new LoadingDialog();
        loadingDialog.initDialog(context);
        loadingDialog.startDialog();
        if (!NetUtil.isNetworkAvailable((Activity) context)) {
            loadingDialog.stopDialog();
            ToastUtil.getShortToast((Activity) context, "网络异常,请检查网络配置",2000);
            return;
        }


    }


    public abstract void MyOnSuccess(String result,String msg);

    public abstract void MyOnFailure(String msg);

    @Override
    public void onFailure(Call call, IOException e) {
        LogUtil.i("MyNetCallBack", "请求失败：" + e.toString());
        loadingDialog.stopDialog();
        mMsg=e.toString();
        handler.sendEmptyMessage(FAIL);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String resBody = response.body().string().toString();
        LogUtil.i("MyNetCallBack", "请求成功结果：" + resBody);
        try {
            JSONObject json = new JSONObject(resBody);
            String code = json.getString("repCode");
            mMsg = json.get("repMsg").toString();
            LogUtil.i("MyNet", "请求成功code：" + code + ":mMsg" + mMsg);
            if (RestResponseCode.TOKEN_FAIL.equals(code)) {     //token失效 跳转登录界面
                handler.sendEmptyMessage(TOKENFAIL);
            } else if (RestResponseCode.SUCCESS.equals(code)) {   //请求成功
                String data = json.get("repData").toString();
                LogUtil.i("MyNetCallBack", "请求成功结果：" + data);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("date", data);
                message.setData(bundle);
                message.what = SUCCESS;
                handler.sendMessage(message);
            } else {     //请求失败
                handler.sendEmptyMessage(FAIL);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.i("MyNetCallBack", "请求成功解析异常：" + resBody);
            ToastUtil.getShortToast((Activity) context,"服务器异常，请联系工程师",1000);
        } finally {
            loadingDialog.stopDialog();
        }
    }
}
