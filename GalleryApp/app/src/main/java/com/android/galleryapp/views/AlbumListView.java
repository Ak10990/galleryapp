package com.android.galleryapp.views;

import com.android.galleryapp.models.Album;

import java.util.List;

public interface AlbumListView extends MainView {

    void onListPopulated();

    void setAlbumList(List<Album> albumList);

    void showMessage(String error);

    void setNoDataView(String message);
}