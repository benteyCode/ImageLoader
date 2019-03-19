package com.bentey.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.bentey.image.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ImageLoader";

    String url = "http://p3.pstatp.com/origin/pgc-image/15220293999066398493c24";

    private ImageView iv1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        iv1 = findViewById(R.id.iv_1);

        toolbar.setTitle("ImageLoader");

        ImageLoader.load(url, iv1);
    }
}
