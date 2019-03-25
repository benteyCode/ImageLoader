package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import com.bentey.image.option.ImageRequestOptions;
import com.bentey.image.util.Util;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import static com.bentey.image.option.ImageRequestOptions.DEFAULT_VALUE;

/**
 * 注意：
 * 1)ImageView尽量设置固定的宽高，如果在xml中ImageView的宽高设置为wrap_content，
 *   图片会根据scaleType和屏幕尺寸像素放大或缩小到一定的尺寸缓存到disk memory中，
 *   而不是ImageView的实际大小
 * 2)尽量传Activity或者Fragment，Glide会监听activity、fragment的生命周期去启动、停止请求
 *
 * @author : bentey
 * @date : 2019/3/19
 * @see <a href="https://github.com/bumptech/glide/wiki">glide:官方文档</a>
 */
public class GlideImageLoader implements ILoader {
    
    private static final String TAG = "GlideImageLoader";

    private Application application;
    private GlideRequest<Drawable> glideRequest;

    public GlideImageLoader(Application application) {
        this.application = application;
    }

    private GlideRequest getGlideRequest(Context context) {
        // tip:不要做判空处理，否则只会加载一次
        //if (glideRequest == null) {
        //    glideRequest = GlideApp.with(context).asDrawable();
        //}
        return GlideApp.with(context).asDrawable();
    }

    private GlideRequest getGlideRequest(Fragment fragment) {
        return GlideApp.with(fragment).asDrawable();
    }

    @Override
    public void load(String url, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        load(imageView.getContext(), url, imageView, new ImageRequestOptions());
    }

    @Override
    public void load(Context context, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkContextNull(context)) {
            return;
        }
        applyOption(getGlideRequest(context).load(url), imageRequestOptions)
            .into(imageView);
    }

    @Override
    public void load(Fragment supportFragment, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        applyOption(getGlideRequest(supportFragment).load(url), imageRequestOptions)
            .into(imageView);
    }

    @Override
    public void load(@DrawableRes int resourceId, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        load(imageView.getContext(), resourceId, imageView, new ImageRequestOptions());
    }

    @Override
    public void load(Context context, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkContextNull(context)) {
            return;
        }
        applyOption(getGlideRequest(context).load(Util.resourceId2Uri(context, resourceId)), imageRequestOptions)
            .into(imageView);
    }

    @Override
    public void load(Fragment supportFragment, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        applyOption(getGlideRequest(supportFragment).load(Util.resourceId2Uri(imageView.getContext(), resourceId)), imageRequestOptions)
            .into(imageView);
    }

    private GlideRequest applyOption(GlideRequest glideRequest,
        ImageRequestOptions imageRequestOptions) {
        if (imageRequestOptions == null) {
            return glideRequest;
        }

        if (imageRequestOptions.getPlaceholder() != DEFAULT_VALUE) {
            glideRequest.placeholder(imageRequestOptions.getPlaceholder());
        }
        if (imageRequestOptions.getError() != DEFAULT_VALUE) {
            glideRequest.error(imageRequestOptions.getError());
        }
        if (imageRequestOptions.getFallback() != DEFAULT_VALUE) {
            glideRequest.fallback(imageRequestOptions.getFallback());
        }
        if (imageRequestOptions.isCenterCrop()) {
            glideRequest.centerCrop();
        }
        if (imageRequestOptions.isFitCenter()) {
            glideRequest.fitCenter();
        }
        //if (imageRequestOptions.isDiskCacheStrategy()) {
        //    glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
        //}
        if (imageRequestOptions.isDontAnimate()) {
            glideRequest.dontAnimate();
        }
        if (imageRequestOptions.isDontTransform()) {
            glideRequest.dontTransform();
        }

        return glideRequest;
    }

    private class GlideImageViewTarget extends DrawableImageViewTarget {
        GlideImageViewTarget(ImageView view) {
            super(view);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            super.onLoadFailed(errorDrawable);
            Log.d(TAG, "onLoadFailed: " + errorDrawable);
        }

        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable
            Transition<? super Drawable> transition) {
            super.onResourceReady(resource, transition);
            Log.d(TAG, "onResourceReady: ");
        }
    }
}
