package com.android.galleryapp.interactor;

import com.android.galleryapp.GalleryApp;
import com.android.galleryapp.api.AlbumApiClient;
import com.android.galleryapp.api.AlbumApiService;
import com.android.galleryapp.listeners.AlbumLoadFinishedListener;
import com.android.galleryapp.models.Album;
import com.android.galleryapp.models.AlbumDb;
import com.android.galleryapp.utils.Constants;
import com.android.galleryapp.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumlListInteractorImpl implements AlbumListInteractor {

    @Override
    public void loadData(AlbumLoadFinishedListener listener) {
        if (GalleryApp.getprefs().getBoolean(Constants.IS_FIRST_LOAD, false)) {
            loadAlbumsFromDb(listener);
        } else {
            if (NetworkUtils.isInternetConnected) {
                loadAlbumsFromApi(listener);
            } else{
                listener.onApiError("No Network");
            }
        }
    }

    private void loadAlbumsFromDb(AlbumLoadFinishedListener listener) {
        RealmResults<AlbumDb> list = GalleryApp.getRealm().allObjects(AlbumDb.class);
        ArrayList<Album> albumList = new ArrayList<>();
        for (AlbumDb albumDb : list) {
            albumList.add(new Album().getAlbumFromDb(albumDb));
        }
        listener.onDbSuccess(albumList);
    }

    private void loadAlbumsFromApi(final AlbumLoadFinishedListener listener) {
        AlbumApiClient apiClient = new AlbumApiClient();
        AlbumApiService apiService = apiClient.getApiService();
        final Call<List<Album>> call = apiService.listAlbums();
        if (call != null) {
            call.enqueue(new Callback<List<Album>>() {
                @Override
                public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
//                    int statusCode = response.code();
                    List<Album> albumList = response.body();
                    if (albumList.size() > 0) {
                        listener.onApiSuccess(albumList);
                        feedIntoDb(albumList);
                    } else {
                        listener.onApiError("No Results Found");
                    }
                }

                @Override
                public void onFailure(Call<List<Album>> call, Throwable t) {
                    listener.onApiError(t.getMessage());
                }
            });
        }
    }

    public void feedIntoDb(List<Album> albumList) {
        Realm realm = GalleryApp.getRealm();
        ArrayList<AlbumDb> list = new ArrayList<>();
        for (Album album : albumList) {
            list.add(album.getAlbumDb());
        }

        realm.beginTransaction();
        realm.copyToRealm(list);
        realm.commitTransaction();

        GalleryApp.getprefs().edit().putBoolean(Constants.IS_FIRST_LOAD, true).apply();
    }
}