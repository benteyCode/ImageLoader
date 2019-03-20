package com.bentey.imageloader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.bentey.imageloader.R;
import com.bentey.imageloader.base.BaseActivity;
import com.bentey.imageloader.model.ImageInfo;
import com.bentey.imageloader.consant.LoadImageType;

public class MainActivity extends BaseActivity {

    String url = "http://p3.pstatp.com/origin/pgc-image/15220293999066398493c24";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btn_url) Button btnUrl;

    @Override
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        toolbar.setTitle("ImageLoader");
    }

    @OnClick({ R.id.btn_url })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_url:
                ImageShowDetailActivity.intent(this, softData(LoadImageType.URL, url));
                break;
        }
    }

    private ImageInfo softData(String type, String url) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setType(type);
        imageInfo.setUrl(url);
        return imageInfo;
    }

}
