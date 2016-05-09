package com.android.galleryapp.listeners;

import com.android.galleryapp.models.Album;

import java.util.List;

public interface AlbumLoadFinishedListener {

    void onApiSuccess(List<Album> albumList);

    void onApiError(String error);

    void onDbSuccess(List<Album> albumList);

}