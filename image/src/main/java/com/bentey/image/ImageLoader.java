package com.bentey.image;

import android.app.Application;
import android.net.Uri;
import android.widget.ImageView;

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

    public static void load(String url, ImageView imageView, ImageLoaderOption imageLoaderOptions) {
        iLoader.load(imageView.getContext(), url, imageView, imageLoaderOptions);
    }

    public static void load(Uri uri, ImageView imageView) {
        iLoader.load(uri, imageView);
    }

    public static void load(Uri uri, ImageView imageView, ImageLoaderOption imageLoaderOptions) {
        iLoader.load(imageView.getContext(), uri, imageView, imageLoaderOptions);
    }
}
