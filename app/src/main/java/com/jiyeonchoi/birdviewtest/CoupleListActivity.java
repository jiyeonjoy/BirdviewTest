package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.jiyeonchoi.birdviewtest.databinding.ActivityCoupleListBinding;
import com.jiyeonchoi.birdviewtest.databinding.ActivityMainBinding;

public class CoupleListActivity extends AppCompatActivity {

    private ActivityCoupleListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_couple_list);
        binding.setCoupleList(this);


    }   // onCreate 끝

}
