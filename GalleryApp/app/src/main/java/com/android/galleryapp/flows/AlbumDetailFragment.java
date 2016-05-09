package com.android.galleryapp.flows;


import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.galleryapp.R;
import com.android.galleryapp.common.BaseFragment;
import com.android.galleryapp.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

public class AlbumDetailFragment extends BaseFragment {

    public static AlbumDetailFragment newInstance(Bundle data) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_details, container, false);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        String uri = "";
        if (getArguments() != null) {
            uri = getArguments().getString(Constants.DETAIL_URL);
        }

        SimpleDraweeView detailImageview = (SimpleDraweeView) view.findViewById(R.id.detail_imageview);
        if (!TextUtils.isEmpty(uri)) {
            detailImageview.setImageURI(Uri.parse(uri));
        }
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

}
