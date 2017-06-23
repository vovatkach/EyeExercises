package com.vovatkach2427gmail.eyeexercises.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vovatkach2427gmail.eyeexercises.DB.DataBaseWorker;
import com.vovatkach2427gmail.eyeexercises.Model.DateModel;
import com.vovatkach2427gmail.eyeexercises.R;

import java.util.Calendar;

public class FinishAct extends AppCompatActivity {
    ImageView btnGoToMainMenu;
    Button btnOkFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_act);
        btnGoToMainMenu=(ImageView) findViewById(R.id.ibGoToMaimMenu);
        btnOkFinish=(Button)findViewById(R.id.btnOkFinish);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar=Calendar.getInstance();
                DateModel dateModel=new DateModel(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
                DataBaseWorker dataBaseWorker=new DataBaseWorker(FinishAct.this);
                dataBaseWorker.addDate(dateModel);
                dataBaseWorker.close();
            }
        });
        thread.start();
        //---------------------------------------
        btnGoToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(btnGoToMainMenu,View.SCALE_X,1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(btnGoToMainMenu,View.SCALE_Y,1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(30);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent=new Intent(FinishAct.this, MainMenu.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
        btnOkFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
