package com.android.galleryapp.models;

public class Album {

    private String id;
    private String albumId;
    private String title;
    private String thumbnailUrl;
    private String url;

    public Album() {
    }

    public Album(String id, String title, String albumId, String thumbnailUrl, String url) {
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

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", albumId='" + albumId + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public AlbumDb getAlbumDb() {
        return new AlbumDb(this.id, this.title, this.albumId, this.thumbnailUrl, this.url);
    }

    public Album getAlbumFromDb(AlbumDb album) {
        id = album.getId();
        title = album.getTitle();
        albumId = album.getAlbumId();
        thumbnailUrl = album.getThumbnailUrl();
        url = album.getUrl();
        return this;
    }
}