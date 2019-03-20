package com.bentey.imageloader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.InflateException;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : bentey
 * @date : 2019/3/20
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = initView(savedInstanceState);
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定butterknife
                mUnBinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            if (e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑butterknife
        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
        this.mUnBinder = null;
    }
}
