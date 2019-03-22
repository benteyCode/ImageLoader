package com.bentey.imageloader.model;

import android.support.annotation.DrawableRes;
import java.io.Serializable;

/**
 * @author : bentey
 * @date : 2019/3/20
 */
public class ImageInfo implements Serializable {

    public String type;

    public String url;

    public @DrawableRes int resourceId;

    public ImageInfo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
