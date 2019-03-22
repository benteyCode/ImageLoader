package com.bentey.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bentey.image.option.ImageRequestOptions;

/**
 * @author : bentey
 * @date : 2019/3/19
 */
public interface ILoader {

    void load(String url, ImageView imageView);

    void load(Context context, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions);

    void load(Fragment supportFragment, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions);

    void load(@DrawableRes int resourceId, ImageView imageView);

    void load(Context context, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions);

    void load(Fragment supportFragment, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions);
}
