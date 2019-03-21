package com.bentey.image;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bentey.image.callback.ImageLoaderCallback;
import com.bentey.image.util.Util;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bentey.image.ImageLoaderOption.INVALID_VALUE;

/**
 * 注意：
 * 1)ImageView尽量设置固定的宽高，如果在xml中ImageView的宽高设置为wrap_content，图片会根据scaleType和屏幕尺寸像素放大或缩小到一定的尺寸缓存到disk memory中，而不是ImageView的实际大小
 * 2)尽量传Activity或者Fragment，Glide会监听activity、fragment的生命周期去启动、停止请求
 *
 * @author : bentey
 * @date : 2019/3/19
 * @see <a href="https://github.com/bumptech/glide/wiki">glide:官方文档</a>
 */
public class GlideImageLoader implements ILoader {

    Application application;
    private static ImageLoaderCallback<Drawable> mImageLoaderCallback;

    private static RequestListener<Drawable> mRequestListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model,
            Target<Drawable> target, boolean isFirstResource) {
            if (mImageLoaderCallback != null) {
                mImageLoaderCallback.failure(e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
            DataSource dataSource, boolean isFirstResource) {
            if (mImageLoaderCallback != null) {
                mImageLoaderCallback.success(resource);
            }
            return false;
        }
    };

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
        RequestBuilder<Drawable> requestBuilder = getRequestManager(context, imageView).load(url);
        applyOption(requestBuilder, imageLoaderOption);
        convertTypeRequest(url, requestBuilder, imageLoaderOption);
        requestBuilder.into(imageView);
    }

    @Override
    public void load(Fragment supportFragment, String url, ImageView imageView,
        ImageLoaderOption imageLoaderOption) {
        if (imageView == null || !Util.checkFragmentNull(supportFragment)) {
            return;
        }
        RequestBuilder<Drawable> requestBuilder =
            getRequestManager(supportFragment, imageView).load(url);
        applyOption(requestBuilder, imageLoaderOption);
        convertTypeRequest(url, requestBuilder, imageLoaderOption);
        requestBuilder.into(imageView);
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
            .into(imageView);
    }

    @Override
    public void loadToBitmap(String url, final ImageLoaderCallback<Drawable> imageLoaderCallback) {
        loadToBitmap(application, url, new ImageLoaderOption(), imageLoaderCallback);
    }

    @Override
    public void loadToBitmap(Context context, String url, ImageLoaderOption imageLoaderOption,
        ImageLoaderCallback<Drawable> imageLoaderCallback) {
        if (!Util.checkContextNull(context)) {
            return;
        }
        this.mImageLoaderCallback = imageLoaderCallback;
        RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(url);
        applyOption(requestBuilder, imageLoaderOption);
        convertTypeRequest(url, requestBuilder, imageLoaderOption);
        requestBuilder.listener(mRequestListener);
    }

    @Override
    public void loadToBitmap(Fragment supportFragment, String url,
        ImageLoaderOption imageLoaderOption,
        ImageLoaderCallback<Drawable> imageLoaderCallback) {
        if (!Util.checkFragmentNull(supportFragment)) {
            return;
        }
        this.mImageLoaderCallback = imageLoaderCallback;
        RequestBuilder<Drawable> requestBuilder = Glide.with(supportFragment).load(url);
        applyOption(requestBuilder, imageLoaderOption);
        convertTypeRequest(url, requestBuilder, imageLoaderOption);
        requestBuilder.listener(mRequestListener);
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

    private RequestBuilder<Drawable> applyOption(RequestBuilder<Drawable> requestBuilder,
        ImageLoaderOption imageLoaderOption) {
        if (requestBuilder == null || imageLoaderOption == null) {
            return requestBuilder;
        }

        RequestOptions requestOptions = new RequestOptions();
        if (imageLoaderOption.getPlaceHolder() != INVALID_VALUE) {
            requestOptions.placeholder(imageLoaderOption.getPlaceHolder());
        }
        if (imageLoaderOption.getErrorHolder() != INVALID_VALUE) {
            requestOptions.error(imageLoaderOption.getErrorHolder());
        }
        if (imageLoaderOption.getResizeWidth() != INVALID_VALUE
            && imageLoaderOption.getResizeHeight() != INVALID_VALUE) {
            requestOptions.override(imageLoaderOption.getResizeWidth(),
                imageLoaderOption.getResizeHeight());
        }
        if (imageLoaderOption.getRadius() != INVALID_VALUE) {
            requestOptions.bitmapTransform(
                new RoundedCornersTransformation(imageLoaderOption.getRadius(), 0));
        }
        requestOptions.skipMemoryCache(imageLoaderOption.isSkipMemoryCache())
            .diskCacheStrategy(imageLoaderOption.isSkipDiskCache() ?
                DiskCacheStrategy.NONE : DiskCacheStrategy.DATA);
        if (imageLoaderOption.isCircle()) {
            requestOptions.circleCrop();
        }
        if (!imageLoaderOption.isAnimate()) {
            requestOptions.dontAnimate();
        }

        return requestBuilder.apply(requestOptions);
    }

    private RequestBuilder<Drawable> convertTypeRequest(String url,
        RequestBuilder<Drawable> requestBuilder,
        ImageLoaderOption imageLoaderOption) {
        RequestOptions requestOptions = new RequestOptions();
        if (!TextUtils.isEmpty(url) && url.contains(".gif")) {
            requestOptions =
                new RequestOptions().diskCacheStrategy(imageLoaderOption.isSkipDiskCache() ?
                    DiskCacheStrategy.NONE : DiskCacheStrategy.DATA);
        }
        return requestBuilder.apply(requestOptions);
    }
}
