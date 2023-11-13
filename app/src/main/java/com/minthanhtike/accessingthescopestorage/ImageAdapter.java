package com.minthanhtike.accessingthescopestorage;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    Context context;
    List<ImagesData> imagesDataList;
    int spanCount;

    public ImageAdapter(Context context, List<ImagesData> uriList,int spanCount) {
        this.context = context;
        this.imagesDataList = uriList;
        this.spanCount=spanCount;
    }
    View view;
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.image_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Uri uri=Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                Long.toString(imagesDataList.get(position).getId()));
        Glide.with(context).load(uri).into(holder.imageView);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        switch (spanCount){
            case 3:
                holder.itemView.getLayoutParams().width= displayMetrics.widthPixels/3-20;
                holder.itemView.getLayoutParams().height=displayMetrics.heightPixels/3;
            break;
            case 4:
                holder.itemView.getLayoutParams().width=displayMetrics.widthPixels/4-20;
                holder.itemView.getLayoutParams().height=displayMetrics.heightPixels/4;
            break;
            case 6:
                holder.itemView.getLayoutParams().width= displayMetrics.widthPixels/6-20;
                holder.itemView.getLayoutParams().height=displayMetrics.heightPixels/8;
            break;
        }
        holder.itemView.setOnClickListener(v -> {
            ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(
                ((Activity) context).getParent(),
                    new Pair<>(view.findViewById(R.id.image_iv_detail),
                            ImagesDetailsAndEdit.VIEW_NAME)
            );
            Intent intent=new Intent(context.getApplicationContext(), ImagesDetailsAndEdit.class);
            intent.putExtra("IMAGES_ID",imagesDataList.get(position).getId());
            intent.putExtra("IMAGES_DATA_URI",imagesDataList.get(position).getDataUri());
            intent.putExtra("IMAGES_BUCKET",imagesDataList.get(position).getBucket());
            intent.putExtra("IMAGES_DATE",imagesDataList.get(position).getDate());
            intent.putExtra("IMAGES_NAME",imagesDataList.get(position).getName());
            intent.putExtra("IMAGES_SIZE",imagesDataList.get(position).getSize());
            intent.putExtra("IMAGES_BUCKET_ID",imagesDataList.get(position).getBucketId());
            ActivityCompat.startActivity(((Activity) context).getParent(),intent,
                    activityOptionsCompat.toBundle());
        });


    }

    @Override
    public int getItemCount() {
        return imagesDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_iv);
        }
    }
}
