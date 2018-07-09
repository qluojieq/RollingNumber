package com.yydrobo.rollingnumber;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    AnimatorSet animatorOut;
    AnimatorSet animatorIn;
    ImageView imageView1;
    ImageView imageView2;
    Button switchBtn;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    switchBtn.callOnClick();
                break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.rolling_map1);
        imageView2 = findViewById(R.id.rolling_map2);
        switchBtn = findViewById(R.id.switch_btn);
        animatorOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_out);
        animatorIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_in);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimator();
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        mHandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    boolean mIsShowBack = false;


    int currentShow = 4;

    public void startAnimator() {

        Log.e(TAG,currentShow + " 当前显示的数字 " + currentShow);

        if (!mIsShowBack) {
            animatorOut.setTarget(imageView1);
            animatorIn.setTarget(imageView2);
            switch (currentShow){
                case 4:
                    currentShow = 3;
                    imageView1.setImageResource(R.mipmap.three);
                    imageView2.setImageResource(R.mipmap.three);
                    Log.e(TAG,mIsShowBack + "out " + 3 + "in " + 3);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    currentShow = 2;
                    imageView1.setImageResource(R.mipmap.three);
                    imageView2.setImageResource(R.mipmap.two);
                    Log.e(TAG,mIsShowBack + "out " + 3 + "in " + 3);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    currentShow = 1;
                    imageView1.setImageResource(R.mipmap.two);
                    imageView2.setImageResource(R.mipmap.one);
                    Log.e(TAG,mIsShowBack + "out " + 2 + "in " + 1);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    currentShow = 4;
                    imageView1.setImageResource(R.mipmap.one);
                    imageView2.setImageResource(R.mipmap.three);
                    Log.e(TAG,mIsShowBack + "out " + 1 + "in " + 3);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    break;
            }

            mIsShowBack = true;
        } else { // 背面朝上
            animatorOut.setTarget(imageView2);
            animatorIn.setTarget(imageView1);
            switch (currentShow){
                case 4:
                    currentShow = 3;
                    imageView1.setImageResource(R.mipmap.three);
                    imageView2.setImageResource(R.mipmap.three);
                    Log.e(TAG,mIsShowBack + "out " + 3 + "in " + 3);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                case 3:
                    currentShow = 2;
                    imageView2.setImageResource(R.mipmap.three);
                    imageView1.setImageResource(R.mipmap.two);
                    Log.e(TAG,mIsShowBack + "out " + 3 + "in " + 2);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    currentShow = 1;
                    imageView2.setImageResource(R.mipmap.two);
                    imageView1.setImageResource(R.mipmap.one);
                    Log.e(TAG,mIsShowBack + "out " + 2 + "in " + 1);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    currentShow = 4;
                    imageView2.setImageResource(R.mipmap.one);
                    imageView1.setImageResource(R.mipmap.three);
                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    Log.e(TAG,mIsShowBack + "out " + 1 + "in " + 3);
                    break;
            }

            mIsShowBack = false;
        }
        animatorIn.start();
        animatorOut.start();

    }



}
