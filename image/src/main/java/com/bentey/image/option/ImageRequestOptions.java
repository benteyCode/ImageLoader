package com.bentey.image.option;

/**
 * 加载图片可选配置
 * placeholder, error, fallback, centerCrop, fitCenter,
 * diskCacheStrategy, dontAnimate, dontTransform
 *
 * @author : bentey
 * @date : 2019/3/22
 */
public class ImageRequestOptions {

    public static final int DEFAULT_VALUE = -1;
    private int placeholder = DEFAULT_VALUE;
    private int error = DEFAULT_VALUE;
    private int fallback = DEFAULT_VALUE;
    private int radius = DEFAULT_VALUE;
    private boolean centerCrop = false;
    private boolean fitCenter = false;
    private boolean diskCacheStrategy = false;
    private boolean dontAnimate = false;
    private boolean dontTransform = false;

    public ImageRequestOptions() {
    }

    public ImageRequestOptions(int placeholder, int error, int fallback, int radius,
        boolean centerCrop, boolean fitCenter, boolean diskCacheStrategy,
        boolean dontAnimate, boolean dontTransform) {
        this.placeholder = placeholder;
        this.error = error;
        this.fallback = fallback;
        this.radius = radius;
        this.centerCrop = centerCrop;
        this.fitCenter = fitCenter;
        this.diskCacheStrategy = diskCacheStrategy;
        this.dontAnimate = dontAnimate;
        this.dontTransform = dontTransform;
    }

    public static int getDefaultValue() {
        return DEFAULT_VALUE;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getError() {
        return error;
    }

    public int getFallback() {
        return fallback;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isCenterCrop() {
        return centerCrop;
    }

    public boolean isFitCenter() {
        return fitCenter;
    }

    public boolean isDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public boolean isDontAnimate() {
        return dontAnimate;
    }

    public boolean isDontTransform() {
        return dontTransform;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int placeholder = DEFAULT_VALUE;
        private int error = DEFAULT_VALUE;
        private int fallback = DEFAULT_VALUE;
        private int radius = DEFAULT_VALUE;
        private boolean centerCrop = false;
        private boolean fitCenter = false;
        private boolean diskCacheStrategy = false;
        private boolean dontAnimate = false;
        private boolean dontTransform = false;

        public Builder setPlaceholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder setError(int error) {
            this.error = error;
            return this;
        }

        public Builder setFallback(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder setRadius(int fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder setCenterCrop(boolean centerCrop) {
            this.centerCrop = centerCrop;
            return this;
        }

        public Builder setFitCenter(boolean fitCenter) {
            this.fitCenter = fitCenter;
            return this;
        }

        public Builder setDiskCacheStrategy(boolean diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder setDontAnimate(boolean dontAnimate) {
            this.dontAnimate = dontAnimate;
            return this;
        }

        public Builder setDontTransform(boolean dontTransform) {
            this.dontTransform = dontTransform;
            return this;
        }

        public ImageRequestOptions createOption() {
            return new ImageRequestOptions(placeholder, error, fallback, radius, centerCrop,
                fitCenter, diskCacheStrategy, dontAnimate, dontTransform);
        }
    }
}
