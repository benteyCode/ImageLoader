package com.bentey.imageloader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author : bentey
 * @date : 2019/3/20
 */
public interface IActivity {

    int initView(@Nullable Bundle bundle);

    void initData(@Nullable Bundle bundle);
}
