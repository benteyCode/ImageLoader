package com.bentey.image.callback;

/**
 * @author : bentey
 * @date : 2019/3/20
 */
public interface ImageLoaderCallback<T> {

    void success(T resource);

    void failure(Exception e);
}
