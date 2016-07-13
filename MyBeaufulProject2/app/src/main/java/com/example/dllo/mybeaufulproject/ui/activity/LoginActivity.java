package com.example.dllo.mybeaufulproject.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.ui.fragment.GuideFragment;

/**
 * Created by dllo on 16/7/13.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView imageViewX;
    @Override
    protected int setLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initViews() {
        imageViewX = ByView(R.id.btn_loginClose);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setListeners() {
        imageViewX.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        goTo(LoginActivity.this,MainActivity.class);
    }
}
