package com.anji.plus.summerchenlibrary.utils.baseapp;

import android.content.Intent;
import android.view.View;

import com.anji.plus.summerchenlibrary.R;
import com.anji.plus.summerchenlibrary.utils.ToastUtil;
import com.anji.plus.summerchenlibrary.utils.customview.LoadingLayout;


/**
 * Created by SummerChen on 2018/4/11.
 */

public abstract class BaseDetailFra extends BaseAppFra {
    private LoadingLayout loadingLayout;


    @Override
    public void beforeInitView(View view) {
        initLoadingLayout(view);
    }



    public void initLoadingLayout(View view) {
        if (getContentView() != -1) {
            loadingLayout = view. findViewById(R.id.loading);
        }
    }


    /**
     * 显示加载中
     */
    protected void showRefreshLoading() {
        if (loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.Loading);
        }
    }

    /**
     * 加载成功
     */
    protected void showRefreshSuccess() {
        if (loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.Success);
        }
    }

    /**
     * 加载失败
     */
    protected void showRefreshError() {
        if (loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.Error);
        }
    }


    /**
     * 无数据
     */
    protected void showNoData() {
        if (loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.Empty);
        }
    }

    /**
     * 没有网
     */
    public void showNoNet() {
        if (loadingLayout != null) {
            loadingLayout.setStatus(LoadingLayout.No_Network);
        }
    }


    /**
     * 加载失败，重试按钮
     */
    protected void setMyReloadClick(LoadingLayout.OnReloadListener onReloadListener) {
        if (loadingLayout != null) {
            loadingLayout.setOnReloadListener(onReloadListener);
        }
    }


    //下面是自定义通知

    /**
     * 只有信息
     */
    protected void showToastMessage(int msgId) {
        showToastMessage(getResources().getString(msgId), false);
    }


    /**
     * 自定义通知 只有信息
     */
    protected void showToastMessage(String msg) {
        showToastMessage(msg, false);
    }

    /**
     * 自定义通知
     */
    protected void showToastMessage(String msg, boolean isFinish) {
        ToastUtil.getShortToast(getActivity(), msg, 1000);
        if (isFinish) {
            getActivity().finish();
        }
    }

    /**
     * 自定义通知  有图片 有信息的 图片暂时没写
     */
    protected void showToastMessage(int imageId, String msg) {
        showToastMessage(imageId, msg, false);
    }


    /**
     * 自定义通知 有图片 有信息的 可以返回上个界面
     */
    protected void showToastMessage(int imageId, String msg, boolean isFinish) {
        ToastUtil.getShortToast(getActivity(), msg, 1000);
        if (isFinish) {
            getActivity().finish();
        }
    }

    //   下面的是可以重写的方法

    /**
     * @param intent
     */
    @Override
    public void initIntentParam(Intent intent) {

    }


    @Override
    public void setViewStatus() {

    }


}
