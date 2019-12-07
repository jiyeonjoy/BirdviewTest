package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.Adapter.CoupleListRCAdabter;
import com.jiyeonchoi.birdviewtest.Data_VO.CoupleListItem;
import com.jiyeonchoi.birdviewtest.Data_VO.DataArray;
import com.jiyeonchoi.birdviewtest.Data_VO.HobbyListItem;
import com.jiyeonchoi.birdviewtest.databinding.ActivityCoupleListBinding;

import java.util.ArrayList;

public class CoupleListActivity extends AppCompatActivity {

    /* 데이터바인딩 */
    private ActivityCoupleListBinding binding;

    //
    /* RecyclerView */
    CoupleListRCAdabter coupleListRCAdabter;
    ArrayList<CoupleListItem> coupleArray = new ArrayList<>();
    String hobbyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_couple_list);
        binding.setCoupleList(this);

        /* 툴바 수정 */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                       // 뒤로가기 버튼 세팅
        getSupportActionBar().setTitle(R.string.coupleListTitle);                                    // 툴바 이름 세팅

        /* 데이터 수신 후 타이틀 변경 */
        Intent intent = getIntent();
        hobbyName = intent.getExtras().getString("hobby", "");
        binding.hobby.setText(hobbyName);

        /* RecyclerView */
        coupleListRCAdabter = new CoupleListRCAdabter(this, coupleArray);
        binding.coupleListRc.setAdapter(coupleListRCAdabter);
        binding.coupleListRc.setLayoutManager(new LinearLayoutManager(this));


        /* RecyclerView 정렬 */
        coupleRCSave();


    }   // onCreate 끝


    /* RecyclerView 정렬 */
    public void coupleRCSave() {

        /* 사람 100명일 때 */
        if (DataArray.peopleCount == 100) {

            for (int i = 0; i < DataArray.t100hobbyList.size(); i++) {
                if (DataArray.t100hobbyList.get(i).equals(hobbyName)) {
                    coupleArray.add(new CoupleListItem(DataArray.t100coupleList.get(i) + " - " + DataArray.t100coupleList2.get(i),
                            DataArray.t100peopleList.get(DataArray.t100coupleList.get(i) - 1), DataArray.t100peopleList.get(DataArray.t100coupleList2.get(i) - 1)));
                }
            }
            coupleListRCAdabter.notifyDataSetChanged();

        } /* 사람 10000명일 때 */ else if (DataArray.peopleCount == 10000) {

            for (int i = 0; i < DataArray.t10000hobbyList.size(); i++) {
                if (DataArray.t10000hobbyList.get(i).equals(hobbyName)) {
                    coupleArray.add(new CoupleListItem(DataArray.t10000coupleList.get(i) + " - " + DataArray.t10000coupleList2.get(i),
                            DataArray.t10000peopleList.get(DataArray.t10000coupleList.get(i) - 1), DataArray.t10000peopleList.get(DataArray.t10000coupleList2.get(i) - 1)));
                }
            }
            coupleListRCAdabter.notifyDataSetChanged();

        } /* 사람 500000명일 때 */ else {

            for (int i = 0; i < DataArray.t500000hobbyList.size(); i++) {
                if (DataArray.t500000hobbyList.get(i).equals(hobbyName)) {
                    coupleArray.add(new CoupleListItem(DataArray.t500000coupleList.get(i) + " - " + DataArray.t500000coupleList2.get(i),
                            DataArray.t500000peopleList.get(DataArray.t500000coupleList.get(i) - 1), DataArray.t500000peopleList.get(DataArray.t500000coupleList2.get(i) - 1)));
                }
            }
            coupleListRCAdabter.notifyDataSetChanged();

        }
    }


    /* 뒤로가기 버튼 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {                                                                 // toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}