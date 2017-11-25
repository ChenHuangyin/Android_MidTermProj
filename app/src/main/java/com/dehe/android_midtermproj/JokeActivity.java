package com.dehe.android_midtermproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;


/**
 * Created by going on 2017/11/5.
 */

public class JokeActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private GestureDetector detector; // 声明一个手势检测器
    private ViewFlipper flipper; //声明一个ViewFlipper实例
    private Animation animation[]; //声明一个动画数组，用于ViewFlipper指定切换动画效果
    final int MINMUN_DISTANCE = 50; //声明手势动作点之间的最小距离

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        detector = new GestureDetector(this, this);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        //为ViewFlipper添加3个ImageView组件
        flipper.addView(addImageView(R.mipmap.img1));
        flipper.addView(addImageView(R.mipmap.img2));
        flipper.addView(addImageView(R.mipmap.img3));
        flipper.addView(addImageView(R.mipmap.img4));
        loadAnimation();

        //返回按键
        ImageButton backButton = (ImageButton)findViewById(R.id.back_joke);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 初始化Animation数组
    private void loadAnimation() {
        animation = new Animation[4];
        animation[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animation[1] = AnimationUtils.loadAnimation(this,R.anim.right_out);
        animation[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animation[3] = AnimationUtils.loadAnimation(this,R.anim.left_out);
    }
    //定义添加ImageView的方法
    private ImageView addImageView(int resId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX() > MINMUN_DISTANCE){
            flipper.setInAnimation(animation[0]);
//            flipper.setOutAnimation(animation[1]);
            flipper.showPrevious();
            return true;
        }
        else if(e2.getX() - e1.getX() > MINMUN_DISTANCE){
            flipper.setInAnimation(animation[2]);
//            flipper.setOutAnimation(animation[3]);
            flipper.showNext();
            return true;
        }
        return false;
    }
}
