package com.bentey.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bentey.image.callback.ImageLoaderCallback;

/**
 * @author : bentey
 * @date : 2019/3/19
 */
public interface ILoader {

    void load(String url, ImageView imageView);

    void load(Context context, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption);

    void load(Fragment supportFragment, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption);

    void load(Uri uri, ImageView imageView);

    void load(Context context, Uri uri, ImageView imageView, ImageLoaderOption imageLoaderOption);

    void load(Fragment supportFragment, Uri uri, ImageView imageView,
        ImageLoaderOption imageLoaderOption);

    void loadToBitmap(String url, ImageLoaderCallback<Drawable> imageLoaderCallback);

    void loadToBitmap(Context context, String url, ImageLoaderOption imageLoaderOption,
        ImageLoaderCallback<Drawable> imageLoaderCallback);

    void loadToBitmap(Fragment supportFragment, String url, ImageLoaderOption imageLoaderOption,
        ImageLoaderCallback<Drawable> imageLoaderCallback);

}
