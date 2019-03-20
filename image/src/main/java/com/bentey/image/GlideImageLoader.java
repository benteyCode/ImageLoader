package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bentey.image.callback.ImageLoaderCallback;
import com.bentey.image.exception.ContextNullException;
import com.bentey.image.util.Util;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bentey.image.ImageLoaderOption.INVALID_VALUE;

/**
 * 注意：
 * 1)ImageView尽量设置固定的宽高，如果在xml中ImageView的宽高设置为wrap_content，图片会根据scaleType和屏幕尺寸像素放大或缩小到一定的尺寸缓存到disk memory中，而不是ImageView的实际大小
 * 2)尽量传Activity或者Fragment，Glide会监听activity、fragment的生命周期去启动、停止请求，如果是与页面无关的后台下载动作则用Application的Context
 *
 * @author : bentey
 * @date : 2019/3/19
 * @see <a href="https://github.com/bumptech/glide/wiki">glide:官方文档</a>
 */
public class GlideImageLoader implements ILoader {

    Application application;

    public GlideImageLoader(Application application) {
        this.application = application;
    }

    @Override
    public void load(String url, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        load(imageView.getContext(), url, imageView, new ImageLoaderOption());
    }

    @Override
    public void load(Context context, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        if (imageView == null || !Util.checkContextNull(context)) {
            return;
        }
        DrawableTypeRequest request = getRequestManager(context, imageView).load(url);
        applyOption(request, imageLoaderOption);
        convertTypeRequest(url, request, imageLoaderOption);
        request.dontAnimate().into(imageView);
    }

    @Override
    public void load(Fragment supportFragment, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        DrawableTypeRequest request = getRequestManager(supportFragment, imageView).load(url);
        applyOption(request, imageLoaderOption);
        convertTypeRequest(url, request, imageLoaderOption);
        request.dontAnimate()
            .into(imageView);
    }

    @Override
    public void load(Uri uri, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        load(imageView.getContext(), uri, imageView, new ImageLoaderOption());
    }

    @Override
    public void load(Context context, Uri uri, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        if (imageView == null || !Util.checkContextNull(context)) {
            return;
        }
        applyOption(getRequestManager(context, imageView).load(Util.parse(uri)),
            imageLoaderOption)
            .dontAnimate()
            .into(imageView);
    }

    @Override
    public void load(Fragment supportFragment, Uri uri, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        applyOption(getRequestManager(supportFragment, imageView).load(Util.parse(uri)),
            imageLoaderOption)
            .dontAnimate()
            .into(imageView);
    }

    @Override
    public void loadToBitmap(String url, final ImageLoaderCallback<Bitmap> imageLoaderCallback) {
        loadToBitmap(application, url, new ImageLoaderOption(), imageLoaderCallback);
    }

    @Override
    public void loadToBitmap(Context context, String url, ImageLoaderOption imageLoaderOption,
        final ImageLoaderCallback<Bitmap> imageLoaderCallback) {
        if (!Util.checkContextNull(context)) {
            imageLoaderCallback.failure(new ContextNullException("context为null"));
        }
        final DrawableTypeRequest request = Glide.with(context).load(url);
        applyOption(request, imageLoaderOption);
        convertTypeRequest(url, request, imageLoaderOption);
        request.asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource,
                GlideAnimation<? super Bitmap> glideAnimation) {
                if (imageLoaderCallback != null) {
                    imageLoaderCallback.success(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (imageLoaderCallback != null) {
                    imageLoaderCallback.failure(e);
                }
            }
        });
    }

    @Override
    public void loadToBitmap(Fragment supportFragment, String url,
        ImageLoaderOption imageLoaderOption, final ImageLoaderCallback<Bitmap> imageLoaderCallback) {
        if (!Util.checkFragmentNull(supportFragment)) {
            imageLoaderCallback.failure(new ContextNullException("context为null"));
        }
        final DrawableTypeRequest request = Glide.with(supportFragment).load(url);
        applyOption(request, imageLoaderOption);
        convertTypeRequest(url, request, imageLoaderOption);
        request.asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource,
                GlideAnimation<? super Bitmap> glideAnimation) {
                if (imageLoaderCallback != null) {
                    imageLoaderCallback.success(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (imageLoaderCallback != null) {
                    imageLoaderCallback.failure(e);
                }
            }
        });
    }

    @Override
    public void clearAllMemoryCache() {
        Glide.with(application).onLowMemory();
    }

    private RequestManager getRequestManager(Context context, ImageView imageView) {
        RequestManager requestManager;
        if (context != null) {
            requestManager = Glide.with(context);
        } else {
            if (imageView != null) {
                requestManager = Glide.with(imageView.getContext());
            } else {
                requestManager = Glide.with(application);
            }
        }
        return requestManager;
    }

    private RequestManager getRequestManager(Fragment fragment, ImageView imageView) {
        RequestManager requestManager;
        if (fragment != null) {
            requestManager = Glide.with(fragment);
        } else {
            if (imageView != null) {
                requestManager = Glide.with(imageView.getContext());
            } else {
                requestManager = Glide.with(application);
            }
        }
        return requestManager;
    }

    private DrawableTypeRequest applyOption(DrawableTypeRequest request,
        ImageLoaderOption imageLoaderOption) {
        if (request == null || imageLoaderOption == null) {
            return request;
        }
        if (imageLoaderOption.getPlaceHolder() != INVALID_VALUE) {
            request.placeholder(imageLoaderOption.getPlaceHolder());
        }
        if (imageLoaderOption.getErrorHolder() != INVALID_VALUE) {
            request.error(imageLoaderOption.getErrorHolder());
        }
        if (imageLoaderOption.getResizeWidth() != INVALID_VALUE
            && imageLoaderOption.getResizeHeight() != INVALID_VALUE) {
            request.override(imageLoaderOption.getResizeWidth(),
                imageLoaderOption.getResizeHeight());
        }
        request.skipMemoryCache(imageLoaderOption.isSkipMemoryCache())
            .diskCacheStrategy(imageLoaderOption.isSkipDiskCache() ?
                DiskCacheStrategy.NONE : DiskCacheStrategy.RESULT);
        if (imageLoaderOption.isCircle()) {
            request.bitmapTransform(new CropCircleTransformation(application));
        }
        if (imageLoaderOption.getRadius() != INVALID_VALUE) {
            request.bitmapTransform(
                new RoundedCornersTransformation(application, imageLoaderOption.getRadius(), 0));
        }
        return request;
    }

    private DrawableTypeRequest convertTypeRequest(String url, DrawableTypeRequest request,
        ImageLoaderOption imageLoaderOption) {
        if (!TextUtils.isEmpty(url) && url.contains(".gif")) {
            request.diskCacheStrategy(imageLoaderOption.isSkipDiskCache() ?
                DiskCacheStrategy.NONE
                : DiskCacheStrategy.SOURCE);
        }
        return request;
    }
}
