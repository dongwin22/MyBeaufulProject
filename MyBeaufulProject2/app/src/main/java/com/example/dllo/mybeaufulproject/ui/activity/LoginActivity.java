package com.example.dllo.mybeaufulproject.ui.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.dllo.mybeaufulproject.R;


/**
 * Created by dllo on 16/7/13.
 * 这里是登录首页的Activity
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageButton imagebtn_X;
    @Override
    protected int setLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initViews() {
        imagebtn_X = ByView(R.id.btn_loginClose);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setListeners() {

        imagebtn_X.setOnClickListener(this);



}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_loginClose:
                goTo(this,MainActivity.class);
                break;
        }


    }
}