package com.android.galleryapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AlbumDb extends RealmObject {

    @PrimaryKey
    private String id;
    private String albumId;
    private String title;
    private String thumbnailUrl;
    private String url;

    public AlbumDb() {
    }

    public AlbumDb(String id, String title, String albumId, String thumbnailUrl, String url) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}