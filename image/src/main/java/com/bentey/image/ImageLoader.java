package com.bentey.image;

import android.app.Application;
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
}
