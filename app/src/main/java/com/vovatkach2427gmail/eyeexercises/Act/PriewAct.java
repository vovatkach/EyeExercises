package com.vovatkach2427gmail.eyeexercises.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vovatkach2427gmail.eyeexercises.R;

public class PriewAct extends AppCompatActivity {
    CarouselView carouselView;
    int[] imgs={
            R.drawable.priew_act_carosel_img1,
            R.drawable.priew_act_carosel_img2,
            R.drawable.priew_act_carosel_img3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priew_act);
        carouselView=(CarouselView)findViewById(R.id.cvPriewAct);
        carouselView.setPageCount(imgs.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });
    }
}
