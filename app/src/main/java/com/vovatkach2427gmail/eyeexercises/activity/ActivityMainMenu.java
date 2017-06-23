package com.vovatkach2427gmail.eyeexercises.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.database.DataBaseWorker;
import com.vovatkach2427gmail.eyeexercises.model.DateModel;
import com.vovatkach2427gmail.eyeexercises.model.StatisticsModel;
import com.vovatkach2427gmail.eyeexercises.R;

import java.util.List;

public class ActivityMainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button btnStartTraining;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        btnStartTraining = (Button) findViewById(R.id.btnStartTraining);
        tvInfo = (TextView) findViewById(R.id.tvMainMenuInfo);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker = new DataBaseWorker(ActivityMainMenu.this);
                List<StatisticsModel> dateModelList = dataBaseWorker.loadStatisticsAll();
                dataBaseWorker.close();
                final String result;
                if (!dateModelList.isEmpty()) {
                    DateModel date = dateModelList.get(dateModelList.size() - 1).getDate();
                    result = DateModel.lastTrainingTime(date);
                } else {
                    result = "Ви ще не тренувались\nПочніть свою першу тренеровку";
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvInfo.setText(result);
                    }
                });
            }
        });
        thread.start();
        //-----------------------------------------------------
        btnStartTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTraining=new Intent(ActivityMainMenu.this,ActivityTraining.class);
              //  Intent startTraining=new Intent(ActivityMainMenu.this,ActivityFinish.class);
                startActivity(startTraining);
                overridePendingTransition(R.anim.in_left, R.anim.out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_statistic:
                Intent goToStat = new Intent(ActivityMainMenu.this, ActivityStatistics.class);
                startActivity(goToStat);
                overridePendingTransition(R.anim.nav_in, R.anim.nav_out);
                break;
            case R.id.nav_notification:
                Intent goToNotification=new Intent(ActivityMainMenu.this,ActivityNotification.class);
                startActivity(goToNotification);
                overridePendingTransition(R.anim.nav_in, R.anim.nav_out);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
