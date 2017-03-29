package com.vovatkach2427gmail.eyeexercises;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.eyeexercises.Act.FinishAct;

/**
 * Created by vovat on 26.03.2017.
 */

public class TrainingAnimator {
    private int currectExercises;
    private int onCornersCount;
    private Activity activity;
    private MediaPlayer mediaPlayer;
    private  ImageView img;
    private TextView tvTop;
    private TextView tvDown;
    private int width;
    private int height;
    private final float speed=0.56f;
    public TrainingAnimator(Activity act, ImageView img1, TextView tvTop1, TextView tvDown1, int width1, int height1)
    {
        currectExercises=0;
        onCornersCount=0;
        activity=act;
        img=img1;
        tvTop=tvTop1;
        tvDown=tvDown1;
        width=width1;
        height=height1;
        mediaPlayer=MediaPlayer.create(activity,R.raw.sound);
    }
    public void restExercises()
    {
        animTips("Закрийте очі до сигналу або кліпайте","Розслабтесь");
        ObjectAnimator objectAnimatorScaleX=ObjectAnimator.ofFloat(img,View.SCALE_X,0.8f,1.2f);
        objectAnimatorScaleX.setDuration(3000);
        objectAnimatorScaleX.setRepeatCount(5);
        objectAnimatorScaleX.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator objectAnimatorScaleY=ObjectAnimator.ofFloat(img,View.SCALE_Y,0.8f,1.2f);
        objectAnimatorScaleY.setDuration(3000);
        objectAnimatorScaleY.setRepeatCount(5);
        objectAnimatorScaleY.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(objectAnimatorScaleX).with(objectAnimatorScaleY);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mediaPlayer.start();
                switch (currectExercises)
                {
                    case 0:
                        UpDownExercises();
                        currectExercises++;
                        break;
                    case 1:
                        RightLeftExercises();
                        currectExercises++;
                        break;
                    case 2:
                        OnCornersExercises();
                        onCornersCount=0;
                        currectExercises++;
                        break;
                    case 3:
                        OnCornersExercisesConversely();
                        onCornersCount=0;
                        currectExercises++;
                        break;
                    case 4:
                        LookDistantObject();
                        currectExercises++;
                        break;
                    case 5:
                        Intent intent=new Intent(activity,FinishAct.class);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.in_left,R.anim.out_right);
                        break;
                }
            }
        });

    }

    private void animTips(String text1, String text2)
    {
        tvTop.setText(text1);
        tvDown.setText(text2);
        tvTop.setVisibility(View.VISIBLE);
        tvDown.setVisibility(View.VISIBLE);
        ObjectAnimator animTvTopShow=ObjectAnimator.ofFloat(tvTop, View.ALPHA,0.0f,1.0f);
        ObjectAnimator animTvEndShow=ObjectAnimator.ofFloat(tvDown, View.ALPHA,0.0f,1.0f);
        animTvTopShow.setDuration(1000);
        animTvEndShow.setDuration(1000);
        AnimatorSet animatorSetShow=new AnimatorSet();
        animatorSetShow.play(animTvTopShow).with(animTvEndShow);
        Interpolator interpolatorShow=new LinearOutSlowInInterpolator();
        animatorSetShow.setInterpolator(interpolatorShow);


        ObjectAnimator animTvTopGone=ObjectAnimator.ofFloat(tvTop, View.ALPHA,1.0f,0.0f);
        ObjectAnimator animTvEndGone=ObjectAnimator.ofFloat(tvDown, View.ALPHA,1.0f,0.0f);
        animTvTopGone.setDuration(10000);
        animTvEndGone.setDuration(10000);
        final AnimatorSet animationSetGone=new AnimatorSet();
        animationSetGone.play(animTvTopGone).with(animTvEndGone);
        Interpolator interpolatorGone=new FastOutLinearInInterpolator();
        animationSetGone.setInterpolator(interpolatorGone);

        animatorSetShow.start();
        animatorSetShow.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationSetGone.start();

            }
        });
        animationSetGone.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                 tvTop.setVisibility(View.GONE);
                tvDown.setVisibility(View.GONE);
                tvTop.setAlpha(0);
                tvDown.setAlpha(0);
            }
        });
    }

    private void UpDownExercises()
    {
        animTips("Вверх-вниз","Слідкуйте очима за вказівником");
        int size=height/2;
        int fraction=height/35;
        ObjectAnimator objectAnimatorUdDown=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,0,-(size-fraction),size-fraction,0);
        Interpolator interpolator=new LinearInterpolator();
        objectAnimatorUdDown.setDuration(getCountTime(2*height));
        objectAnimatorUdDown.setRepeatCount(3);
        objectAnimatorUdDown.setInterpolator(interpolator);
        objectAnimatorUdDown.start();
        objectAnimatorUdDown.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                restExercises();
            }
        });
    }
    private void RightLeftExercises()
    {
        animTips("Вправо-вліво","Слідкуйте очима за вказівником");
        int size=width/2;
        int fraction=width/35;
        ObjectAnimator objectAnimatorUdDown=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,0,-(size-fraction),size-fraction,0);
        Interpolator interpolator=new LinearInterpolator();
        objectAnimatorUdDown.setDuration(getCountTime(2*width));
        objectAnimatorUdDown.setRepeatCount(3);
        objectAnimatorUdDown.setInterpolator(interpolator);
        objectAnimatorUdDown.start();
        objectAnimatorUdDown.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                restExercises();
            }
        });
    }
    private void OnCornersExercises()
    {
        onCornersCount=0;
        animTips("Траектрія прямокутник","Слідкуйте очима за вказівником");
        int sizeWeidth=width/2;
        int sizeHeight=height/2;
        int fractionWeidth=width/22;
        int fractionHeight=height/22;
        Interpolator interpolator=new LinearInterpolator();
        //----------------------------
        final ObjectAnimator objectAnimatorGoToTopRight=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,-(sizeWeidth-fractionWeidth),(sizeWeidth-fractionWeidth));
        objectAnimatorGoToTopRight.setDuration(getCountTime(width));
        objectAnimatorGoToTopRight.setInterpolator(interpolator);
        //-----------------------------
        final ObjectAnimator objectAnimatorGoToBottomRight=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,-(sizeHeight-fractionHeight),(sizeHeight-fractionHeight));
        objectAnimatorGoToBottomRight.setDuration(getCountTime(height));
        objectAnimatorGoToBottomRight.setInterpolator(interpolator);
        //-----------------------------
        final ObjectAnimator objectAnimatorGoToBottomLeft=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,(sizeWeidth-fractionWeidth),-(sizeWeidth-fractionWeidth));
        objectAnimatorGoToBottomLeft.setDuration(getCountTime(width));
        objectAnimatorGoToBottomLeft.setInterpolator(interpolator);
        //----------------------------
        final ObjectAnimator objectAnimatorGoToTopLeft=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,(sizeHeight-fractionHeight),-(sizeHeight-fractionHeight));
        objectAnimatorGoToTopLeft.setDuration(getCountTime(height));
        objectAnimatorGoToTopLeft.setInterpolator(interpolator);
        //------------------------------------
        ///робити всякі круті штуки////
        //-----рух в верхній правий кут
        ObjectAnimator objectAnimatorGoToTopLeftX=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,0,-(sizeWeidth-fractionWeidth));
        ObjectAnimator objectAnimatorGoToTopLeftY=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,0,-(sizeHeight-fractionHeight));
        AnimatorSet animatorSetGoToTopLeft=new AnimatorSet();
        animatorSetGoToTopLeft.setInterpolator(interpolator);
        animatorSetGoToTopLeft.setDuration(getCountTime(getHypotenuse()));
        animatorSetGoToTopLeft.play(objectAnimatorGoToTopLeftX).with(objectAnimatorGoToTopLeftY);
        animatorSetGoToTopLeft.start();
        //рух з правого кута в центр
        ObjectAnimator objectAnimatorGoToCenterX=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,-(sizeWeidth-fractionWeidth),0);
        ObjectAnimator objectAnimatorGoToCenterY=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,-(sizeHeight-fractionHeight),0);
        final AnimatorSet animatorSetGoToCenter=new AnimatorSet();
        animatorSetGoToCenter.setInterpolator(interpolator);
        animatorSetGoToCenter.setDuration(getHypotenuse());
        animatorSetGoToCenter.play(objectAnimatorGoToCenterX).with(objectAnimatorGoToCenterY);
        //----------------------------------------
        animatorSetGoToTopLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToTopRight.start();
            }
        });
        objectAnimatorGoToTopRight.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToBottomRight.start();
            }
        });
        objectAnimatorGoToBottomRight.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToBottomLeft.start();
            }
        });
        objectAnimatorGoToBottomLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToTopLeft.start();
            }
        });
        objectAnimatorGoToTopLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(onCornersCount<1)
                {
                    objectAnimatorGoToTopRight.start();
                    onCornersCount++;
                }
                else  {
                    animatorSetGoToCenter.start();
                    onCornersCount=0;
                }
                }
        });
        animatorSetGoToCenter.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                restExercises();
            }
        });
    }
    private void OnCornersExercisesConversely()
    {
        onCornersCount=0;
        animTips("Траектрія прямокутник в інший бік","Слідкуйте очима за вказівником");
        int sizeWeidth=width/2;
        int sizeHeight=height/2;
        int fractionWeidth=width/22;
        int fractionHeight=height/22;
        Interpolator interpolator=new LinearInterpolator();
        //---------------------------------------
        //-----рух в верхній правий кут
        ObjectAnimator objectAnimatorGoToTopLeftX=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,0,-(sizeWeidth-fractionWeidth));
        ObjectAnimator objectAnimatorGoToTopLeftY=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,0,-(sizeHeight-fractionHeight));
        AnimatorSet animatorSetGoToTopLeft=new AnimatorSet();
        animatorSetGoToTopLeft.setInterpolator(interpolator);
        animatorSetGoToTopLeft.setDuration(getCountTime(getHypotenuse()));
        animatorSetGoToTopLeft.play(objectAnimatorGoToTopLeftX).with(objectAnimatorGoToTopLeftY);
        animatorSetGoToTopLeft.start();
        //-----рух по кутах
        final ObjectAnimator objectAnimatorGoToBottomLeft=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,-(sizeHeight-fractionHeight),(sizeHeight-fractionHeight));
        objectAnimatorGoToBottomLeft.setDuration(getCountTime(height));
        objectAnimatorGoToBottomLeft.setInterpolator(interpolator);
        //--------------------
        final ObjectAnimator objectAnimatorGoToBottomRight=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,-(sizeWeidth-fractionWeidth),(sizeWeidth-fractionWeidth));
        objectAnimatorGoToBottomRight.setDuration(getCountTime(width));
        objectAnimatorGoToBottomRight.setInterpolator(interpolator);
        //---------------------
        final ObjectAnimator objectAnimatorGoToTopRight=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,(sizeHeight-2*fractionWeidth),-(sizeHeight-fractionWeidth));
        objectAnimatorGoToTopRight.setDuration(getCountTime(height));
        objectAnimatorGoToTopRight.setInterpolator(interpolator);
        //--------------------------------------------------------
        final ObjectAnimator objectAnimatorGoToTopLeft=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,(sizeWeidth-fractionWeidth),-(sizeWeidth-fractionWeidth));
        objectAnimatorGoToTopLeft.setDuration(getCountTime(width));
        objectAnimatorGoToTopLeft.setInterpolator(interpolator);
        //------------------------------------------------------
        ObjectAnimator objectAnimatorGoToCenterX=ObjectAnimator.ofFloat(img,View.TRANSLATION_X,-(sizeWeidth-fractionWeidth),0);
        ObjectAnimator objectAnimatorGoToCenterY=ObjectAnimator.ofFloat(img,View.TRANSLATION_Y,-(sizeHeight-fractionHeight),0);
        final AnimatorSet animatorSetGoToCenter=new AnimatorSet();
        animatorSetGoToCenter.setInterpolator(interpolator);
        animatorSetGoToCenter.setDuration(getCountTime(getHypotenuse()));
        animatorSetGoToCenter.play(objectAnimatorGoToCenterX).with(objectAnimatorGoToCenterY);
        //--------------------------------------------------------
        animatorSetGoToTopLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToBottomLeft.start();
            }
        });
        objectAnimatorGoToBottomLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToBottomRight.start();
            }
        });
        objectAnimatorGoToBottomRight.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToTopRight.start();
            }
        });
        objectAnimatorGoToTopRight.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                objectAnimatorGoToTopLeft.start();
            }
        });
        objectAnimatorGoToTopLeft.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(onCornersCount<1)
                {
                    objectAnimatorGoToBottomLeft.start();
                    onCornersCount++;
                }
                else  {
                    animatorSetGoToCenter.start();
                    onCornersCount=0;
                }
            }
        });
        animatorSetGoToCenter.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                restExercises();
            }
        });


    }
    private void LookDistantObject()
    {
        FrameLayout.LayoutParams layoutParamsTop=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTop.setMargins(0,height/10,0,0);
        tvTop.setLayoutParams(layoutParamsTop);
        FrameLayout.LayoutParams layoutParamsBottom=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsBottom.setMargins(0,height-(height/5),0,0);
        tvDown.setLayoutParams(layoutParamsBottom);
        //////-------------------
        tvTop.setText("Подивіться на предмет який знаходиться від вас на відстані 5-10 м");
        tvDown.setText("Розглядайте предмет до сигналу");
        tvTop.setVisibility(View.VISIBLE);
        tvDown.setVisibility(View.VISIBLE);
        ObjectAnimator animTvTopShow=ObjectAnimator.ofFloat(tvTop, View.ALPHA,0.0f,1.0f);
        ObjectAnimator animTvEndShow=ObjectAnimator.ofFloat(tvDown, View.ALPHA,0.0f,1.0f);
        animTvTopShow.setDuration(1000);
        animTvEndShow.setDuration(1000);
        AnimatorSet animatorSetShow=new AnimatorSet();
        animatorSetShow.play(animTvTopShow).with(animTvEndShow);
        Interpolator interpolatorShow=new LinearOutSlowInInterpolator();
        animatorSetShow.setInterpolator(interpolatorShow);
        animatorSetShow.start();
        ////-----------------
       // animTips(tvTop,tvDowm,"Подивіться на предмет який знаходиться від вас на відстані 5-10 м","Розглядайте предмет до сигналу");
        img.setImageResource(R.drawable.distant_object);
        ObjectAnimator objectAnimatorScaleX=ObjectAnimator.ofFloat(img,View.SCALE_X,0.9f,1.1f);
        objectAnimatorScaleX.setDuration(3000);
        objectAnimatorScaleX.setRepeatCount(7);
        objectAnimatorScaleX.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator objectAnimatorScaleY=ObjectAnimator.ofFloat(img,View.SCALE_Y,0.9f,1.1f);
        objectAnimatorScaleY.setDuration(3000);
        objectAnimatorScaleY.setRepeatCount(7);
        objectAnimatorScaleY.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(objectAnimatorScaleX).with(objectAnimatorScaleY);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvTop.setVisibility(View.GONE);
                tvDown.setVisibility(View.GONE);
                tvTop.setAlpha(0);
                tvDown.setAlpha(0);
                img.setImageResource(R.drawable.eye0);
                restExercises();
            }
        });
    }
    private int getCountTime(int size)
    {
        return Math.round(size/speed);
    }
    private int getHypotenuse()
    {
        int a=(int) Math.sqrt(height*height+width*width);
        a=a/2;
        return a;
    }

}
