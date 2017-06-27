package com.vovatkach2427gmail.eyeexercises.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.notification.MyNotification;

public class ActivityWelcome extends AppCompatActivity {
    TextView tvWelcomeName;

    ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcomeName = (TextView) findViewById(R.id.tvWelcomeName);

        sendNotifTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Interpolator interpolator = new AccelerateInterpolator();
        animator = ObjectAnimator.ofFloat(tvWelcomeName, View.ALPHA, 0.0f, 1.0f);
        animator.setDuration(4000);
        animator.setInterpolator(interpolator);
        tvWelcomeName.setVisibility(View.VISIBLE);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(ActivityWelcome.this, ActivityMainMenu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_left, R.anim.out_right);
            }
        });
    }

    private void sendNotifTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("notification", MODE_PRIVATE);
        if (sharedPreferences.getInt("repeat_time", 100) == 100) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("repeat_time", 1);
            editor.apply();

            MyNotification myNotification=new MyNotification(this);
            myNotification.start(1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        animator.removeAllListeners();

    }
}
