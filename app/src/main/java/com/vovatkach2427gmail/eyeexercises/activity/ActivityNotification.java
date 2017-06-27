package com.vovatkach2427gmail.eyeexercises.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.notification.MyNotification;
import com.vovatkach2427gmail.eyeexercises.notification.NotificationService;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityNotification extends AppCompatActivity {

    @BindView(R.id.ivNavBackFromNotification)
    ImageView ivNavBack;

    @BindView(R.id.rbHour1)
    RadioButton rbHour1;

    @BindView(R.id.rbHour2)
    RadioButton rbHour2;

    @BindView(R.id.rbHour3)
    RadioButton rbHour3;

    @BindView(R.id.rbHour4)
    RadioButton rbHour4;

    @BindView(R.id.rbHour5)
    RadioButton rbHour5;

    @BindView(R.id.rbHour6)
    RadioButton rbHour6;

    @BindView(R.id.rbHourNever)
    RadioButton rbHourNever;

    @BindView(R.id.btnChangeNotifTime)
    Button btnChange;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    int notifTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        notifTime = getNotifTime();
        checkTime();

        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        btnChange.setOnClickListener(onClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ivNavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivNavBack, View.SCALE_X, 1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivNavBack, View.SCALE_Y, 1.0f, 0.85f, 1.15f, 1.0f);
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

    private int getNotifTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("notification", MODE_PRIVATE);
        return sharedPreferences.getInt("repeat_time", 1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int num = getTimeFromRadioButton();

            SharedPreferences sharedPreferences=getSharedPreferences("notification",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("repeat_time",num);
            editor.commit();
            notifTime=getNotifTime();

            MyNotification myNotification=new MyNotification(ActivityNotification.this);
            myNotification.changeRepeatTime(notifTime);

            btnChange.setEnabled(false);
            btnChange.setAlpha(0.5f);
        }
    };

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            int num = getTimeFromRadioButton();

            if (notifTime != num) {
                btnChange.setEnabled(true);
                btnChange.setAlpha(1f);
            } else {
                btnChange.setEnabled(false);
                btnChange.setAlpha(0.5f);
            }

        }
    };

    private void checkTime() {
        switch (notifTime) {
            case 1:
                rbHour1.setChecked(true);
                break;
            case 2:
                rbHour2.setChecked(true);
                break;
            case 3:
                rbHour3.setChecked(true);
                break;
            case 4:
                rbHour4.setChecked(true);
                break;
            case 5:
                rbHour5.setChecked(true);
                break;
            case 6:
                rbHour6.setChecked(true);
                break;
            default:
                rbHourNever.setChecked(true);
                break;
        }
    }

    private int getTimeFromRadioButton() {
        int num = 0;

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbHour1:
                num = 1;
                break;
            case R.id.rbHour2:
                num = 2;
                break;
            case R.id.rbHour3:
                num = 3;
                break;
            case R.id.rbHour4:
                num = 4;
                break;
            case R.id.rbHour5:
                num = 5;
                break;
            case R.id.rbHour6:
                num = 6;
                break;
            default:
                num = 0;
                break;
        }
        return num;
    }
}
