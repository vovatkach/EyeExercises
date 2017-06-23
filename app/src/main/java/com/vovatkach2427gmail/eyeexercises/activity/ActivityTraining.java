package com.vovatkach2427gmail.eyeexercises.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.TrainingAnimator;

public class ActivityTraining extends AppCompatActivity {

    protected PowerManager.WakeLock mWakeLock;

    ImageView imgPointer;
    TextView tvTopTip;
    TextView tvBottomTip;
    int width = 0;
    int height = 0;

    ImageView btnClose;

    TrainingAnimator trainingAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
/*
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        imgPointer = (ImageView) findViewById(R.id.ivPointer);
        tvTopTip = (TextView) findViewById(R.id.tvTopTip);
        tvBottomTip = (TextView) findViewById(R.id.tvBottomTip);
        btnClose = (ImageView) findViewById(R.id.btnCloseTraining);

        tvTopTip.setAlpha(0);
        tvBottomTip.setAlpha(0);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
    }

    @Override
    protected void onResume() {
        super.onResume();
        trainingAnimator = new TrainingAnimator(ActivityTraining.this, imgPointer, tvTopTip, tvBottomTip, width, height);
        trainingAnimator.restExercises();
        ///----------------------------
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(btnClose, View.SCALE_X, 1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(btnClose, View.SCALE_Y, 1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(30);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        onBackPressed();
                        overridePendingTransition(R.anim.in_left, R.anim.out_right);
                    }
                });
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        trainingAnimator.stop();
        trainingAnimator = null;

        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imgPointer, View.TRANSLATION_X, imgPointer.getHeight(), height / 2);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imgPointer, View.TRANSLATION_Y, imgPointer.getWidth(), width / 2);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.setDuration(50);
        animatorSet.play(objectAnimatorX).with(objectAnimatorY);
        animatorSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  this.mWakeLock.release();
          getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}
