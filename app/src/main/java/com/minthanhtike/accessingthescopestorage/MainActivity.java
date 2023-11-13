package com.minthanhtike.accessingthescopestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageAdapter imageAdapter;
    RecyclerView recyclerView;
    List<ImagesData> imagesDataArrayList =new ArrayList<>();
    ScaleGestureDetector scaleGestureDetector;
    GridLayoutManager normalGrid,smallGrid,largeGrid,currentGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);

        normalGrid=new GridLayoutManager(this,4);
        largeGrid=new GridLayoutManager(this,6);
        smallGrid=new GridLayoutManager(this,3);
        currentGrid=normalGrid;
        scaleGestureDetector=new ScaleGestureDetector(this,new PinchZoomListener());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_MEDIA_IMAGES
            },PackageManager.PERMISSION_GRANTED);
        }else {
            loadImages(currentGrid);
        }

    }
    private static final int MYREQUEST=101;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MYREQUEST){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show();
                loadImages(normalGrid);
            }else {
                Toast.makeText(this, "Need Permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadImages(GridLayoutManager gridLayoutManager){
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            return false;
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        imagesDataArrayList=ImageUriRetriever.getAllImages(this);
        recyclerView.addItemDecoration(new VerticalSpacingDecoration(20));
        imageAdapter=new ImageAdapter(this, imagesDataArrayList,gridLayoutManager.getSpanCount());
        recyclerView.setAdapter(imageAdapter);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        return true;
//    }

    public class PinchZoomListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            float gestureFactor=detector.getScaleFactor();
            if (detector.getCurrentSpan()>150 && detector.getTimeDelta()>150){
                if (detector.getCurrentSpan()- detector.getPreviousSpan() < -1){
                    if (currentGrid==smallGrid){
                        loadImages(normalGrid);
                        currentGrid=normalGrid;
                        return true;
                    }else if (currentGrid == normalGrid){
                        loadImages(largeGrid);
                        currentGrid=largeGrid;
                        return true;
                    }
                }else if (detector.getCurrentSpan() - detector.getPreviousSpan()>1){
                    if (currentGrid == largeGrid){
                        loadImages(normalGrid);
                        currentGrid=normalGrid;
                        return true;
                    } else if (currentGrid == normalGrid) {
                        loadImages(smallGrid);
                        currentGrid=smallGrid;
                        return true;
                    }
//                    loadImages(largeGrid);
                }
            }
            return false;
        }

        @Override
        public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(@NonNull ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }
    }
    public class VerticalSpacingDecoration extends RecyclerView.ItemDecoration{
        private final int space;

        VerticalSpacingDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            outRect.right=space;
        }
    }
}



