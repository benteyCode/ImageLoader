package com.bentey.imageloader;

import android.app.Application;
import com.bentey.image.ImageLoader;

/**
 * @author : bentey
 * @date : 2019/3/19
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoader.init(this);
    }
}
