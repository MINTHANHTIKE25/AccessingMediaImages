package com.minthanhtike.accessingthescopestorage;

public class ImagesData {
    String bucketId;
    long id;
    long size;
    String bucket;
    String date;
    String name;
    String dataUri;

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataUri() {
        return dataUri;
    }

    public void setDataUri(String dataUri) {
        this.dataUri = dataUri;
    }

    public ImagesData(String bucketId, long id, long size, String bucket, String date, String name, String dataUri) {
        this.bucketId = bucketId;
        this.id = id;
        this.size = size;
        this.bucket = bucket;
        this.date = date;
        this.name = name;
        this.dataUri = dataUri;
    }
}
