package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.R;
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

        /* 툴바 수정 */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                       // 뒤로가기 버튼 세팅
        getSupportActionBar().setTitle(R.string.hobbyListTitle);                                     // 툴바 이름 세팅

        /*데이터 수신*/
        Intent intent = getIntent();
        peopleCount = intent.getExtras().getInt("peopleCount", 0);

        if (peopleCount == 100) {
            binding.peopleCount.setText(R.string.p100);
        } else if (peopleCount == 10000) {
            binding.peopleCount.setText(R.string.p10000);
        } else {
            binding.peopleCount.setText(R.string.p500000);
        }



    }   // onCreate 끝



    /* 파일 읽기 */
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



    /* 뒤로가기 버튼 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{                                                                 // toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
