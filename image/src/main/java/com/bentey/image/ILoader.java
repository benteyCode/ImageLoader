package com.bentey.image;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

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
}
