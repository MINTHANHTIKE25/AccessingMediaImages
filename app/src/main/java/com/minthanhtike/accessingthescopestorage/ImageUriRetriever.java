package com.minthanhtike.accessingthescopestorage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class ImageUriRetriever {
    public static List<ImagesData> getAllImages(Context context) {
//        List<Uri> imageUris = new ArrayList<>();
        List<ImagesData> imagesData=new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Define the columns you want to retrieve
        String[] projection = {
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA};

        // Define a sorting order (if needed)
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";

        // Query the MediaStore to retrieve all images
        Cursor cursor = contentResolver.query(imageUri, projection, null, null, sortOrder);

        if (cursor != null) {
            String bucketId;
            long id;
            long size;
            String bucket;
            String date;
            String name;
            String dataUri;
            try {
                while (cursor.moveToNext()) {
                    // Build the image URI using the ID
//                    int idIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
//                    long imageId = cursor.getLong(idIndex);
//                    Uri imageContentUri = Uri.withAppendedPath(imageUri, Long.toString(imageId));
//                    imageUris.add(imageContentUri);

                    int bucketIdColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);

                    int idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);

                    int bucketColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

                    int dateColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);

                    int sizeColumn = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);

                    int nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);

                    int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                    bucketId = cursor.getString(bucketIdColumn);
                    bucket = cursor.getString(bucketColumn);
                    date = cursor.getString(dateColumn);
                    size = cursor.getLong(sizeColumn);
                    id = cursor.getLong(idColumn);
                    name = cursor.getString(nameColumn);
                    dataUri = cursor.getString(dataColumn);
                    imagesData.add(new ImagesData(bucketId,id,size,bucket,date,name,dataUri));
                }
            } finally {
                cursor.close();
            }
        }
        return imagesData;
    }

}
