package com.android.galleryapp.flows;

import android.os.Bundle;
import android.os.Message;

import com.android.galleryapp.R;
import com.android.galleryapp.common.BaseActivity;
import com.android.galleryapp.utils.Constants;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchListScreen();
    }

    @Override
    public boolean handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case Constants.AllScreens.DISPLAY_LIST_SCREEN:
                launchListScreen();
                break;
            case Constants.AllScreens.DETAIL_LIST_SCREEN:
                launchDetailScreen(msg.getData());
                break;
        }
        return false;
    }

    private void launchDetailScreen(Bundle data) {
        addFragment(AlbumDetailFragment.newInstance(data), AlbumDetailFragment.class.getSimpleName(), true);
    }

    private void launchListScreen() {
        addFragment(AlbumListFragment.newInstance(), AlbumListFragment.class.getSimpleName(), false);
    }


    @Override
    public void onBackPressed() {
        handleFragmentsBack();
    }

    private void handleFragmentsBack() {
        super.onBackPressed();
    }
}