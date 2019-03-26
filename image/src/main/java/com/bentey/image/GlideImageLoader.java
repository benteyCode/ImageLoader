package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bentey.image.option.ImageRequestOptions;
import com.bentey.image.util.Util;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import static com.bentey.image.option.ImageRequestOptions.DEFAULT_VALUE;

/**
 * 注意：
 * 1)ImageView尽量设置固定的宽高，如果在xml中ImageView的宽高设置为wrap_content，
 * 图片会根据scaleType和屏幕尺寸像素放大或缩小到一定的尺寸缓存到disk memory中，
 * 而不是ImageView的实际大小
 * 2)尽量传Activity或者Fragment，Glide会监听activity、fragment的生命周期去启动、停止请求
 * 3)创建GlideRequest注意事项，不要做判空处理，否则只会加载一次
 * <code>
 * if (glideRequest == null) {
 * glideRequest = GlideApp.with(context).asDrawable();
 * }
 * </code>
 *
 * @author : bentey
 * @date : 2019/3/19
 * @see <a href="https://github.com/bumptech/glide/wiki">glide:官方文档</a>
 */
public class GlideImageLoader implements ILoader {

    private static final String TAG = "GlideImageLoader";

    private Application application;
    private GlideRequest<Drawable> mGlideRequest;

    public GlideImageLoader(Application application) {
        this.application = application;
    }

    private void getGlideRequest(Context context) {
        mGlideRequest = GlideApp.with(context).asDrawable();
    }

    private void getGlideRequest(Fragment fragment) {
        mGlideRequest = GlideApp.with(fragment).asDrawable();
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
        loadImage(context, url, imageView, imageRequestOptions);
    }

    @Override
    public void load(Fragment supportFragment, String url, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        loadImage(supportFragment, url, imageView, imageRequestOptions);
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
        loadImage(context, Util.resourceId2Uri(context, resourceId), imageView,
            imageRequestOptions);
    }

    @Override
    public void load(Fragment supportFragment, @DrawableRes int resourceId, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        loadImage(supportFragment, Util.resourceId2Uri(imageView.getContext(), resourceId),
            imageView, imageRequestOptions);
    }

    private GlideRequest<Drawable> loadImage(Context context, Object obj) {
        getGlideRequest(context);
        return mGlideRequest.load(obj);
    }

    private GlideRequest<Drawable> loadImage(Fragment supportFragment, Object obj) {
        getGlideRequest(supportFragment);
        return mGlideRequest.load(obj);
    }

    private void loadImage(Context context, Object obj, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        mGlideRequest = loadImage(context, obj);
        mGlideRequest = applyOption(imageRequestOptions);
        mGlideRequest.into(imageView);
    }

    private void loadImage(Fragment supportFragment, Object obj, ImageView imageView,
        ImageRequestOptions imageRequestOptions) {
        mGlideRequest = loadImage(supportFragment, obj);
        mGlideRequest = applyOption(imageRequestOptions);
        mGlideRequest.into(imageView);
    }

    private GlideRequest<Drawable> applyOption(ImageRequestOptions imageRequestOptions) {
        if (imageRequestOptions == null) {
            return mGlideRequest;
        }

        if (imageRequestOptions.getPlaceholder() != DEFAULT_VALUE) {
            mGlideRequest.placeholder(imageRequestOptions.getPlaceholder());
        }
        if (imageRequestOptions.getError() != DEFAULT_VALUE) {
            mGlideRequest.error(imageRequestOptions.getError());
        }
        if (imageRequestOptions.getFallback() != DEFAULT_VALUE) {
            mGlideRequest.fallback(imageRequestOptions.getFallback());
        }
        if (imageRequestOptions.isCenterCrop()) {
            mGlideRequest.centerCrop();
        }
        if (imageRequestOptions.isFitCenter()) {
            mGlideRequest.fitCenter();
        }
        if (imageRequestOptions.isDontAnimate()) {
            mGlideRequest.dontAnimate();
        }
        if (imageRequestOptions.isDontTransform()) {
            mGlideRequest.dontTransform();
        }
        if (imageRequestOptions.getStrategy() != null) {
            mGlideRequest.diskCacheStrategy(imageRequestOptions.getStrategy());
        }
        if (imageRequestOptions.getTransformation() != null) {
            mGlideRequest.transform(imageRequestOptions.getTransformation());
        }
        if (imageRequestOptions.isCircle()) {
            mGlideRequest.circleCrop();
        }
        return mGlideRequest;
    }
}
