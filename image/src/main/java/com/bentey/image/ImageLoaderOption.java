package com.bentey.image;

import android.support.annotation.DrawableRes;

/**
 * 加载图片的可选配置
 * placeHolder，placeHolder，resizeWidth，resizeHeight，radius
 * skipMemoryCache，skipDiskCache，circle
 *
 * @author : bentey
 * @date : 2019/3/19
 */
public class ImageLoaderOption {

    public static final int INVALID_VALUE = -1;
    private int placeHolder = INVALID_VALUE;
    private int errorHolder = INVALID_VALUE;
    private int resizeWidth = INVALID_VALUE;
    private int resizeHeight = INVALID_VALUE;
    private int radius = INVALID_VALUE;
    private boolean skipMemoryCache = false;
    private boolean skipDiskCache = false;
    private boolean circle = false;
    private boolean animate = false;

    public ImageLoaderOption() {
    }

    private ImageLoaderOption(int placeHolder, int errorHolder, int resizeWidth, int resizeHeight,
        int radius, boolean skipMemoryCache, boolean skipDiskCache, boolean circle,
        boolean animate) {
        this.placeHolder = placeHolder;
        this.errorHolder = errorHolder;
        this.resizeWidth = resizeWidth;
        this.resizeHeight = resizeHeight;
        this.radius = radius;
        this.skipMemoryCache = skipMemoryCache;
        this.skipDiskCache = skipDiskCache;
        this.circle = circle;
        this.animate = animate;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public int getErrorHolder() {
        return errorHolder;
    }

    public int getResizeWidth() {
        return resizeWidth;
    }

    public int getResizeHeight() {
        return resizeHeight;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isSkipDiskCache() {
        return skipDiskCache;
    }

    public boolean isCircle() {
        return circle;
    }

    public boolean isAnimate() {
        return animate;
    }

    public static ImageLoaderOptionBuilder builder() {
        return new ImageLoaderOptionBuilder();
    }

    public static class ImageLoaderOptionBuilder {

        private int placeHolder = INVALID_VALUE;
        private int errorHolder = INVALID_VALUE;
        private int resizeWidth = INVALID_VALUE;
        private int resizeHeight = INVALID_VALUE;
        private int radius = INVALID_VALUE;
        private boolean skipMemoryCache = false;
        private boolean skipDiskCache = false;
        private boolean circle = false;
        private boolean animate = false;

        public ImageLoaderOptionBuilder setPlaceHolder(@DrawableRes int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public ImageLoaderOptionBuilder setErrorHolder(@DrawableRes int errorHolder) {
            this.errorHolder = errorHolder;
            return this;
        }

        public ImageLoaderOptionBuilder setResizeWidth(int resizeWidth) {
            this.resizeWidth = resizeWidth;
            return this;
        }

        public ImageLoaderOptionBuilder setResizeHeight(int resizeHeight) {
            this.resizeHeight = resizeHeight;
            return this;
        }

        public ImageLoaderOptionBuilder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public ImageLoaderOptionBuilder setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public ImageLoaderOptionBuilder setSkipDiskCache(boolean skipDiskCache) {
            this.skipDiskCache = skipDiskCache;
            return this;
        }

        public ImageLoaderOptionBuilder setCircle(boolean circle) {
            this.circle = circle;
            return this;
        }

        public ImageLoaderOptionBuilder setAnimate(boolean animate) {
            this.animate = animate;
            return this;
        }

        public ImageLoaderOption createOption() {
            return new ImageLoaderOption(placeHolder, errorHolder, resizeWidth, resizeHeight,
                radius, skipMemoryCache, skipDiskCache, circle, animate);
        }
    }
}
