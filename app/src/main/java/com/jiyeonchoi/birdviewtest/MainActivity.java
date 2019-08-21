package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyeonchoi.birdviewtest.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);

        binding.text100.setOnClickListener( v -> goHobbyListIntent(100));

        binding.text10000.setOnClickListener( v -> goHobbyListIntent(10000));

        binding.text500000.setOnClickListener( v -> goHobbyListIntent(50000));

    }   // onCreate 끝


    /* 매칭되는 취미 리스트로 이동 */
    public void goHobbyListIntent(int peopleCount) {
        Intent go_hobby_list = new Intent(getApplication().getApplicationContext(), HobbyListActivity.class);
        go_hobby_list.putExtra("peopleCount", peopleCount);
        startActivity(go_hobby_list);

    }

}