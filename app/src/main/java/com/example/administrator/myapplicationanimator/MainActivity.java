package com.example.administrator.myapplicationanimator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout lly;
    private TextView tv;
    private Button show, hide;
    public int viewH;
    private LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lly = (LinearLayout) findViewById(R.id.lly);
        tv = (TextView) findViewById(R.id.tv);
        hide = (Button) findViewById(R.id.hide);
        show = (Button) findViewById(R.id.show);
        lp = (LinearLayout.LayoutParams) lly.getLayoutParams();
        viewH = lp.height;
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
    }

    public void hide() {
        final ValueAnimator anim = ObjectAnimator.ofFloat(0.0f, 1.0f).setDuration(300);
        ObjectAnimator.ofFloat(tv, "TranslationY", -viewH).setDuration(400).start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                if (lp.height > 0) {
                    lp.height = (int) (viewH - (viewH + lp.height) * cVal);
                    if (lp.height < 0)
                        lp.height = 0;
                    lly.setLayoutParams(lp);
                }
            }
        });

        anim.start();
    }

    public void show() {
        final ValueAnimator aimh = ObjectAnimator.ofFloat(0.0f, 1.0f).setDuration(300);
        ObjectAnimator.ofFloat(tv, "TranslationY", 0).setDuration(400).start();
        aimh.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                if (lp.height < viewH) {
                    lp.height = (int) (0 - (0 - (viewH)) * cVal);
                    lly.setLayoutParams(lp);
                }
            }
        });
        aimh.start();
    }
}
