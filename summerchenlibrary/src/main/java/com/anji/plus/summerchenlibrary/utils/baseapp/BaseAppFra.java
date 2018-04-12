package com.anji.plus.summerchenlibrary.utils.baseapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ChenZhihong on 2017/6/25.
 */

public abstract class BaseAppFra extends Fragment {

    /**
     * 初始化activity传递参数
     */
    public abstract void initIntentParam(Intent intent);

    /**
     * 初始化页面之前的操作，用于不同页面类型的差异化操作，止于模板层，具体页面无需实现
     */
    public abstract void beforeInitView(View view);

    /**
     * 定义页面控件
     */
    public abstract void initView(View view);

    /**
     * 设置页面控件事件和状态
     */
    public abstract void setViewStatus();

    public abstract int getContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentParam(getActivity().getIntent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        beforeInitView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
}
