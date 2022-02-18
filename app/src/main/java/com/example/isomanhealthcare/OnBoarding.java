package com.example.isomanhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.isomanhealthcare.SliderAdapter;
import com.example.isomanhealthcare.R;
import com.example.isomanhealthcare.MainActivity;
import com.example.isomanhealthcare.LoginActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button mulai, nextBtn;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_on_boarding);

        // Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        mulai = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);

        // Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    };

    public void mulaiBtn(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPosition + 1);
    }

    private void addDots(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    };

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;

            if (position == 0) {
                mulai.setVisibility(View.INVISIBLE);
            }
            else if (position == 1) {
                mulai.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                mulai.setAnimation(animation);
                mulai.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}