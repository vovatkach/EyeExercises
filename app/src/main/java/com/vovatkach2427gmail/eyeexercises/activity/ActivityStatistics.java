package com.vovatkach2427gmail.eyeexercises.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.adapter.RVAdapterStatistics;
import com.vovatkach2427gmail.eyeexercises.database.DataBaseWorker;
import com.vovatkach2427gmail.eyeexercises.model.DateModel;
import com.vovatkach2427gmail.eyeexercises.model.StatisticsModel;
import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.notification.MyNotification;
import com.vovatkach2427gmail.eyeexercises.notification.NotificationService;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActivityStatistics extends AppCompatActivity {
    ImageView ivNavigationBack;
    RecyclerView rvStatistics;
    TextView tvUserTraining;
    Button btnClear;

    RVAdapterStatistics adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvStatistics = (RecyclerView) findViewById(R.id.rvStatistics);
        tvUserTraining = (TextView) findViewById(R.id.tvUserTraining);
        btnClear = (Button) findViewById(R.id.btnClearStatistics);
        ivNavigationBack = (ImageView) findViewById(R.id.ivNavBackFormStatistics);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker = new DataBaseWorker(ActivityStatistics.this);
                final List<StatisticsModel> list = dataBaseWorker.loadStatisticsAll();
                dataBaseWorker.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RVAdapterStatistics(list, ActivityStatistics.this);
                        if (!list.isEmpty()) {
                            tvUserTraining.setText(DateModel.getAverageTimeToTraining(list.get(0).getDate(), list.size()));
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityStatistics.this);
                            rvStatistics.setHasFixedSize(true);
                            rvStatistics.setLayoutManager(layoutManager);
                            rvStatistics.setAdapter(adapter);
                        } else {
                            tvUserTraining.setText("не знайдено жодного тренування");
                        }
                    }
                });
            }
        });
        thread.start();
        //--------------------------------------------------
        ivNavigationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivNavigationBack, View.SCALE_X, 1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivNavigationBack, View.SCALE_Y, 1.0f, 0.85f, 1.15f, 1.0f);
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
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                adapter.notifyDataSetChanged();
                tvUserTraining.setText("не знайдено жодного тренування");
                Thread threadClear = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataBaseWorker dataBaseWorker = new DataBaseWorker(ActivityStatistics.this);
                        dataBaseWorker.clearStatistics();
                        dataBaseWorker.close();
                    }
                });
                threadClear.start();
            }
        });

    }
}
