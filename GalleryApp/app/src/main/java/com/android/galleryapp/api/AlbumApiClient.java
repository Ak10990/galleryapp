package com.android.galleryapp.api;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumApiClient {

    /**
     * Api EndPoints
     */
    private static final String API_HOMEPAGE = "https://jsonplaceholder.typicode.com";

    private AlbumApiService apiService;

    public AlbumApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_HOMEPAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build();

        apiService = retrofit.create(AlbumApiService.class);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            Builder okHttpClient = new Builder();
            okHttpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AlbumApiService getApiService() {
        return apiService;
    }

}