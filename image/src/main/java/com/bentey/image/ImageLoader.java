package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bentey.image.callback.ImageLoaderCallback;

/**
 * Initialize the SDK {@link ImageLoader#init(Application)}
 *
 * @author : bentey
 * @date : 2019/3/19
 */
public class ImageLoader {

    private static ILoader iLoader;

    public static void init(Application application) {
        iLoader = new GlideImageLoader(application);
    }

    public static void load(String url, ImageView imageView) {
        iLoader.load(url, imageView);
    }

    public static void load(Context context, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        iLoader.load(context, url, imageView, imageLoaderOption);
    }

    public static void load(Uri uri, ImageView imageView) {
        iLoader.load(uri, imageView);
    }

    public static void load(Context context, Uri uri, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        iLoader.load(context, uri, imageView, imageLoaderOption);
    }

    public static void loadToBitmap(String url, ImageLoaderCallback<Drawable> imageLoaderCallback) {
        iLoader.loadToBitmap(url, imageLoaderCallback);
    }

    public static void loadToBitmap(Context context, String url,
        ImageLoaderOption imageLoaderOption,
        ImageLoaderCallback<Drawable> imageLoaderCallback) {
        iLoader.loadToBitmap(context, url, imageLoaderOption, imageLoaderCallback);
    }
}
