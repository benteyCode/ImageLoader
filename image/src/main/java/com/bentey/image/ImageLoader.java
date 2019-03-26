package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bentey.image.option.ImageRequestOptions;

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
        ImageRequestOptions imageRequestOptions) {
        iLoader.load(context, url, imageView, imageRequestOptions);
    }

    public static void load(Fragment supportFragment, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        iLoader.load(supportFragment, url, imageView, imageRequestOptions);
    }

    public static void load(@DrawableRes int resourceId, ImageView imageView) {
        iLoader.load(resourceId, imageView);
    }

    public static void load(Context context, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        iLoader.load(context, resourceId, imageView, imageRequestOptions);
    }

    public static void load(Fragment supportFragment, @DrawableRes int resourceId,
        ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        iLoader.load(supportFragment, resourceId, imageView, imageRequestOptions);
    }
}
