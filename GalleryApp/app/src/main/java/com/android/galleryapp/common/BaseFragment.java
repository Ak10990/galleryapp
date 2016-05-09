package com.android.galleryapp.common;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.galleryapp.utils.AlertToastUtils;

public abstract class BaseFragment extends Fragment implements Handler.Callback, OnClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view, savedInstanceState);
        initPresenter();
    }

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract void initPresenter();

    protected BaseActivity getCurrActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
    }

    public void showMessage(String message) {
        AlertToastUtils.createToast(getActivity(), message);
    }

    public void showProgress() {
        AlertToastUtils.showProgressDialog(getActivity(), "Loading...");
    }

    public void hideProgress() {
        AlertToastUtils.stopProgressDialog();
    }
}

