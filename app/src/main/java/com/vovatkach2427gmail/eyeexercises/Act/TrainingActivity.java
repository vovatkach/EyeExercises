package com.vovatkach2427gmail.eyeexercises.Act;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.TrainingAnimator;

import java.io.IOException;

public class TrainingActivity extends AppCompatActivity {
    ImageView imgPointer;
    TextView tvTopTip;
    TextView tvBottomTip;
    int width=0;
    int height=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_act);

        imgPointer=(ImageView)findViewById(R.id.ivPointer);
        tvTopTip=(TextView)findViewById(R.id.tvTopTip);
        tvBottomTip=(TextView)findViewById(R.id.tvBottomTip);
        tvTopTip.setAlpha(0);
        tvBottomTip.setAlpha(0);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TrainingAnimator trainingAnimator=new TrainingAnimator(TrainingActivity.this,imgPointer,tvTopTip,tvBottomTip,width,height);
        trainingAnimator.restExercises();
    }
}
