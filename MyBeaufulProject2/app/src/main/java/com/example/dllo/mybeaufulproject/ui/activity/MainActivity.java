package com.example.dllo.mybeaufulproject.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.ui.fragment.ClassifyFragment;
import com.example.dllo.mybeaufulproject.ui.fragment.GuideFragment;
import com.example.dllo.mybeaufulproject.ui.fragment.HotFragment;
import com.example.dllo.mybeaufulproject.ui.fragment.MyFragment;


public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup main_radioGroup;
    private GuideFragment guideFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        main_radioGroup = ByView(R.id.main_radioGroup);

        guideFragment = new GuideFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout,guideFragment).commit();

    }

    @Override
    protected void initDatas() {
        main_radioGroup.setOnCheckedChangeListener(this);
        main_radioGroup.check(R.id.guide_radioButton);

    }

    @Override
    protected void setListeners() {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.guide_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,guideFragment);
                break;
            case R.id.hot_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new HotFragment());
                break;
            case R.id.classify_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new ClassifyFragment());
                break;
            case R.id.my_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new MyFragment());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

    }
}
