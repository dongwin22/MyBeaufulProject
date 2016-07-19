package com.example.dllo.mybeaufulproject.ui.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.mybeaufulproject.R;

/**
 * Created by dllo on 16/7/12.
 * 这里是欢迎页的Activity
 */
public class WelcomeActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView imageView;
    private CountDownTimer timer;


    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        imageView = ByView(R.id.welcome_iv);
    }

    @Override
    protected void initDatas() {

        imageView.setOnClickListener(this);

        timer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                goTo(WelcomeActivity.this,MainActivity.class);

            }
        }.start();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onClick(View v) {
        goTo(WelcomeActivity.this,MainActivity.class);
        finish();
    }
}
