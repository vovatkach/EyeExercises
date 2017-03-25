package com.vovatkach2427gmail.eyeexercises.Act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vovatkach2427gmail.eyeexercises.MainMenu;
import com.vovatkach2427gmail.eyeexercises.R;

public class PriewAct extends AppCompatActivity {
    CarouselView carouselView;
    Button btnGoToMainMenu;
    int[] imgs={
            R.drawable.priew_act_carosel_img1,
            R.drawable.priew_act_carosel_img2,
            R.drawable.priew_act_carosel_img3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priew_act);
        btnGoToMainMenu=(Button)findViewById(R.id.btn_go_to_main_menu);
        carouselView=(CarouselView)findViewById(R.id.cvPriewAct);
        carouselView.setPageCount(imgs.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });
        btnGoToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainMenu=new Intent(PriewAct.this,MainMenu.class);
                startActivity(goToMainMenu);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });
    }
}
