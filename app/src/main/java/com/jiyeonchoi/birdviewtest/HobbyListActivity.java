package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.jiyeonchoi.birdviewtest.databinding.ActivityHobbyListBinding;
import com.jiyeonchoi.birdviewtest.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HobbyListActivity extends AppCompatActivity {

    private ActivityHobbyListBinding binding;

    int peopleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hobby_list);
        binding.setHobbyList(this);

        /*데이터 수신*/
        Intent intent = getIntent();
        peopleCount = intent.getExtras().getInt("peopleCount", 0);
    }

    private String readTxt() {
        String data = null;
        InputStream inputStream = getResources().openRawResource(R.raw.t100);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayOutputStream.toByteArray(),"MS949");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
