package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.jiyeonchoi.birdviewtest.Adapter.CoupleListRCAdabter;
import com.jiyeonchoi.birdviewtest.Adapter.HobbyListRCAdapter;
import com.jiyeonchoi.birdviewtest.ListItem.CoupleListItem;
import com.jiyeonchoi.birdviewtest.ListItem.HobbyListItem;
import com.jiyeonchoi.birdviewtest.R;
import com.jiyeonchoi.birdviewtest.databinding.ActivityCoupleListBinding;
import com.jiyeonchoi.birdviewtest.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class CoupleListActivity extends AppCompatActivity {

    private ActivityCoupleListBinding binding;

    int peopleCount;

    private RecyclerView.Adapter adapter;
    ArrayList<CoupleListItem> items = new ArrayList<>();
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_couple_list);
        binding.setCoupleList(this);


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

    /* 리사이클러뷰 어댑터 설정 */
    private void setRecyclerView(){

        mRecyclerView.setHasFixedSize(true);
        adapter = new CoupleListRCAdabter(getApplicationContext(), items);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
