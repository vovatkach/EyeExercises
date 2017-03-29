package com.vovatkach2427gmail.eyeexercises.Act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.vovatkach2427gmail.eyeexercises.R;

public class FinishAct extends AppCompatActivity {
   ImageButton btnGoToMainMenu;
    Button btnOkFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_act);
        btnGoToMainMenu=(ImageButton) findViewById(R.id.ibGoToMaimMenu);
        btnOkFinish=(Button)findViewById(R.id.btnOkFinish);
        btnGoToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainMenu=new Intent(FinishAct.this,MainMenu.class);
                startActivity(goToMainMenu);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
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
