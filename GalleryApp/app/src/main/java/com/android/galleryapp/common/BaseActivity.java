package com.android.galleryapp.common;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.galleryapp.R;

public abstract class BaseActivity extends AppCompatActivity implements Handler.Callback {

    protected BaseFragment displayedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    protected void addFragment(BaseFragment fragment, String tag, boolean addToBackStack) {
        displayedFragment = fragment;
        int containerID = R.id.frame_list_container;
        int fragEnterAnim = R.anim.slide_in_from_right;
        int fragExitAnim = R.anim.slide_out_to_left;
        int fragPopEnterAnim = R.anim.slide_in_from_left;
        int fragPopExitAnim = R.anim.slide_out_to_right;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(containerID, fragment, tag);
        if (addToBackStack) {
            transaction.setCustomAnimations(fragEnterAnim, fragExitAnim, fragPopEnterAnim, fragPopExitAnim)
                    .addToBackStack(tag);
        }
        transaction.commit();
    }
}

