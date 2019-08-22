package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.Adapter.CoupleListRCAdabter;
import com.jiyeonchoi.birdviewtest.Data_VO.CoupleListItem;
import com.jiyeonchoi.birdviewtest.databinding.ActivityCoupleListBinding;

import java.util.ArrayList;

public class CoupleListActivity extends AppCompatActivity {

    /* 데이터바인딩 */
    private ActivityCoupleListBinding binding;

    /* 데이터 파일 사람 수 */
    int peopleCount;

    /* RecyclerView */
    CoupleListRCAdabter coupleListRCAdabter;
    ArrayList<CoupleListItem> coupleArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_couple_list);
        binding.setCoupleList(this);

        /* 툴바 수정 */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                       // 뒤로가기 버튼 세팅
        getSupportActionBar().setTitle(R.string.hobbyListTitle);                                      // 툴바 이름 세팅

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

        /* RecyclerView */
        coupleListRCAdabter = new CoupleListRCAdabter(this, coupleArray);
        binding.coupleListRc.setAdapter(coupleListRCAdabter);
        binding.coupleListRc.setLayoutManager(new LinearLayoutManager(this));



    }   // onCreate 끝




















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
