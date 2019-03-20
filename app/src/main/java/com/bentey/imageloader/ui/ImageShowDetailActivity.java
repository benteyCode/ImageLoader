package com.bentey.imageloader.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import com.bentey.image.ImageLoader;
import com.bentey.image.ImageLoaderOption;
import com.bentey.image.callback.ImageLoaderCallback;
import com.bentey.imageloader.R;
import com.bentey.imageloader.base.BaseActivity;
import com.bentey.imageloader.config.IntentExtraKey;
import com.bentey.imageloader.config.LoadImageType;
import com.bentey.imageloader.model.ImageInfo;

/**
 * @author : bentey
 * @date : 2019/3/20
 */
public class ImageShowDetailActivity extends BaseActivity {

    @BindView(R.id.image) ImageView image;
    private ImageInfo mImageInfo;

    public static void intent(Context context, ImageInfo imageInfo) {
        Intent intent = new Intent(context, ImageShowDetailActivity.class);
        if (imageInfo != null) {
            intent.putExtra(IntentExtraKey.IMAGE_SHOW_INFO, imageInfo);
        }
        context.startActivity(intent);
    }

    @Override
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_image_show_detail;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        if (getIntent() != null && getIntent().hasExtra(IntentExtraKey.IMAGE_SHOW_INFO)) {
            mImageInfo = (ImageInfo) getIntent().getExtras().get(IntentExtraKey.IMAGE_SHOW_INFO);
        } else {
            mImageInfo = null;
        }

        if (mImageInfo != null) {
            showImageWithType(mImageInfo);
        }
    }

    private void showImageWithType(ImageInfo imageInfo) {
        String type = imageInfo.getType();
        if (LoadImageType.URL.equals(type)) {
            ImageLoader.load(this, imageInfo.getUrl(), image,
                ImageLoaderOption.builder().setCircle(true).createOption());
        } else if (LoadImageType.URI.equals(type)) {
            ImageLoader.load(Uri.parse(imageInfo.getUri()), image);
        } else if (LoadImageType.BITMAP.equals(type)) {
            ImageLoader.loadToBitmap(this, imageInfo.getUrl(),
                ImageLoaderOption.builder().setCircle(true).createOption(),
                new ImageLoaderCallback<Bitmap>() {
                    @Override
                    public void success(Bitmap resource) {
                        // TODO: 2019/3/20
                        Toast.makeText(ImageShowDetailActivity.this, "success", Toast.LENGTH_SHORT)
                            .show();
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });
        }
    }
}
