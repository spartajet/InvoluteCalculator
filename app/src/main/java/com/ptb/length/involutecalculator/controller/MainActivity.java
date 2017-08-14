package com.ptb.length.involutecalculator.controller;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.ptb.length.involutecalculator.calculator.Parameters;
import com.ptb.length.involutecalculator.util.InvolutePageAdapter;
import com.ptb.length.involutecalculator.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContradictionFragment.OnFragmentInteractionListener, ValueFixedFragment.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private List<View> mViewList = new ArrayList<>();
    private LayoutInflater inflater;

    private ValueFixedFragment valueFixedFragment;
    private ContradictionFragment contradictionFragment;
    private Button clearBtn;
    private Button calculateBtn;
    private Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        initView();
    }

    private void initViews() {
        this.valueFixedFragment = new ValueFixedFragment();
        this.contradictionFragment = new ContradictionFragment();
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        InvolutePageAdapter pagerAdapter = new InvolutePageAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(valueFixedFragment);
        pagerAdapter.addFragment(contradictionFragment);
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void initView() {
        this.clearBtn = (Button) findViewById(R.id.clearBtn);
        this.clearBtn.setOnClickListener(v -> {
            Parameters.clear();
            valueFixedFragment.clear();
            contradictionFragment.clear();
        });

        this.calculateBtn = (Button) findViewById(R.id.calculateBtn);
        this.calculateBtn.setOnClickListener((v -> {
            Parameters.calculate();
            valueFixedFragment.showResult();
            contradictionFragment.showResult();
        }));
        this.quitBtn = (Button) findViewById(R.id.QuitBtn);
        this.quitBtn.setOnClickListener(v -> this.finishAffinity());
    }
}
