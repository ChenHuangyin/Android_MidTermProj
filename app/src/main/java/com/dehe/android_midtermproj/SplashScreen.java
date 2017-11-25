package com.dehe.android_midtermproj;

/**
 * Created by lenovo on 2017/11/26.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();
            }
        }, 3000); //启动等待3秒钟
    }
}
