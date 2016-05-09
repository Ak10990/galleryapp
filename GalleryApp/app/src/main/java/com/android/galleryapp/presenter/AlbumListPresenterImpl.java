package com.android.galleryapp.presenter;

import com.android.galleryapp.interactor.AlbumListInteractor;
import com.android.galleryapp.interactor.AlbumlListInteractorImpl;
import com.android.galleryapp.listeners.AlbumLoadFinishedListener;
import com.android.galleryapp.models.Album;
import com.android.galleryapp.views.AlbumListView;

import java.util.List;

public class AlbumListPresenterImpl implements AlbumListPresenter, AlbumLoadFinishedListener {

    private AlbumListView albumListView;
    private AlbumListInteractor albumListInteractor;

    public AlbumListPresenterImpl(AlbumListView albumListView) {
        this.albumListView = albumListView;
        this.albumListInteractor = new AlbumlListInteractorImpl();
    }

    @Override
    public void loadData() {
        albumListView.showProgress();
        albumListInteractor.loadData(this);
    }

    @Override
    public void onApiSuccess(List<Album> albumList) {
        albumListView.hideProgress();
        albumListView.setAlbumList(albumList);
        albumListView.onListPopulated();
    }

    @Override
    public void onApiError(String error) {
        albumListView.hideProgress();
        albumListView.showMessage(error);
        albumListView.setNoDataView(error);
    }

    @Override
    public void onDbSuccess(List<Album> albumList) {
        albumListView.hideProgress();
        albumListView.setAlbumList(albumList);
        albumListView.onListPopulated();
    }

}
