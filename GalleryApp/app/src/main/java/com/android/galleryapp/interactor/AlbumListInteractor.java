package com.android.galleryapp.interactor;

import com.android.galleryapp.listeners.AlbumLoadFinishedListener;

public interface AlbumListInteractor {

    void loadData(AlbumLoadFinishedListener listener);
}