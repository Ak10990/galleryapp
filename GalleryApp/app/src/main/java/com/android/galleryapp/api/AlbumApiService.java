package com.android.galleryapp.api;


import com.android.galleryapp.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApiService {

    @GET("/photos")
    Call<List<Album>> listAlbums();
}