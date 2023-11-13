package com.minthanhtike.accessingthescopestorage;

import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ImagesDetailsAndEdit extends AppCompatActivity {
    public static final String VIEW_NAME="IMAGE:IV:DETAIL";
    ImageView imageView;
    ConstraintLayout constraintLayout;
    TextView idTv, bucketTv, bucketIdTv, sizeTv, dateTv, nameTv, dataUriTv;
//    private final int SWIPE_THRESHOLD = 100;
//    private final int VELOCITY_THRESHOLD = 100;
//    GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_images_details_and_edit);
        imageView = findViewById(R.id.image_iv_detail);
        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                Long.toString(getIntent().getLongExtra("IMAGES_ID", 0)));
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
        imageView.setImageURI(uri);
//        ViewCompat.setTransitionName();

        //showing and hiding the status bar
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        getWindow().getDecorView().setOnApplyWindowInsetsListener((view, windowInsets) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
                        || windowInsets.isVisible(WindowInsetsCompat.Type.statusBars())) {
                    imageView.setOnClickListener(v -> {
                        // Hide both the status bar and the navigation bar.
                        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars());
                        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
                    });
                } else {
                    imageView .setOnClickListener(v -> {
                        // Show both the status bar and the navigation bar.
                        windowInsetsController.show(WindowInsetsCompat.Type.systemBars());
                    });
                }
            }
            return view.onApplyWindowInsets(windowInsets);
        });


        idTv=findViewById(R.id.id_tv);
        bucketTv=findViewById(R.id.bucket_tv);
        bucketIdTv=findViewById(R.id.bucket_id_tv);
        sizeTv=findViewById(R.id.size_tv);
        dateTv=findViewById(R.id.date_tv);
        nameTv=findViewById(R.id.name_tv);
        dataUriTv=findViewById(R.id.data_uri);

        String imgId=Long.toString(getIntent().getLongExtra("IMAGES_ID", 0));
        String imagesDataUri=getIntent().getStringExtra("IMAGES_DATA_URI");
        String imgBucket=getIntent().getStringExtra("IMAGES_BUCKET");
        String imgDate=getIntent().getStringExtra("IMAGES_DATE");
        String imgName=getIntent().getStringExtra("IMAGES_NAME");
        String imgSize=Long.toString(getIntent().getLongExtra("IMAGES_SIZE",0));
        String imagesBucketId=getIntent().getStringExtra("IMAGES_BUCKET_ID");

        idTv.setText(String.format("%s%s", idTv.getText(), imgId));
        bucketTv.setText(String.format("%s%s", bucketTv.getText(), imgBucket));
        dataUriTv.setText(String.format("%s%s", dataUriTv.getText(), imagesDataUri));
        dateTv.setText(String.format("%s%s", dateTv.getText(), imgDate));
        nameTv.setText(String.format("%s%s", nameTv.getText(), imgName));
        sizeTv.setText(String.format("%s%s", sizeTv.getText(), imgSize));
        bucketIdTv.setText(String.format("%s%s", bucketIdTv.getText(), imagesBucketId));
//        gestureDetector = new GestureDetector(this, new GestureDetecting());
//        imageView.setOnTouchListener((v, event) -> {
//            gestureDetector.onTouchEvent(event);
//            return true;
//        });
    }

//    private void swipeDown() {
//        Toast.makeText(this, "This is swipe down!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void swipeUp() {
//        Toast.makeText(this, "This is swipe up!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void swipeLeft() {
//        Toast.makeText(this, "This is swipe left!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void swipeRight() {
//        Toast.makeText(this, "This is swipe right!", Toast.LENGTH_SHORT).show();
//    }


//    class GestureDetecting implements GestureDetector.OnGestureListener {
//
//        @Override
//        public boolean onDown(@NonNull MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public void onShowPress(@NonNull MotionEvent e) {
//
//        }
//
//        @Override
//        public boolean onSingleTapUp(@NonNull MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
//            return false;
//        }
//
//        @Override
//        public void onLongPress(@NonNull MotionEvent e) {
//
//        }
//
//        @Override
//        public boolean onFling(@NonNull MotionEvent initialMotionEvent, @NonNull MotionEvent finalMotionEvent, float velocityX, float velocityY) {
//            boolean result = false;
//            float diffX = finalMotionEvent.getX() - initialMotionEvent.getX();
//            float diffY = finalMotionEvent.getY() - initialMotionEvent.getY();
//            if (Math.abs(diffX) > Math.abs(diffY)) {
//                //that is right and left
//                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > VELOCITY_THRESHOLD) {
//                    if (diffX > 0) {
//                        swipeRight();
//                    } else {
//                        swipeLeft();
//                    }
//                }
//            } else {
//                //that is up and down
//                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > VELOCITY_THRESHOLD) {
//                    if (diffY > 0) {
//                        swipeDown();
//                    } else {
//                        swipeUp();
//                    }
//                }
//            }
//            return result;
//        }
//    }
}

//        translucent effect on the activity
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            WindowInsetsController controller = getWindow().getInsetsController();
//            if (controller != null) {
//                controller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.statusBars());
//                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
//            }
//        } else {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }

//    private void setWindowFlag(Activity activity, final int bits, boolean on) {
//        Window win = activity.getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }