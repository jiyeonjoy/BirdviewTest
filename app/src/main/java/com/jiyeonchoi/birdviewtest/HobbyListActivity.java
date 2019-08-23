package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.MenuItem;
import com.jiyeonchoi.birdviewtest.Adapter.HobbyListRCAdapter;
import com.jiyeonchoi.birdviewtest.Data_VO.DataArray;
import com.jiyeonchoi.birdviewtest.Data_VO.HobbyListItem;
import com.jiyeonchoi.birdviewtest.databinding.ActivityHobbyListBinding;
import java.util.ArrayList;


public class HobbyListActivity extends AppCompatActivity {

    /* 데이터바인딩 */
    private ActivityHobbyListBinding binding;

    /* RecyclerView */
    HobbyListRCAdapter hobbyListRCAdapter;
    ArrayList<HobbyListItem> hobbyArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hobby_list);
        binding.setHobbyList(this);

        /* 툴바 수정 */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                       // 뒤로가기 버튼 세팅
        getSupportActionBar().setTitle(R.string.hobbyListTitle);                                      // 툴바 이름 세팅


        /* 타이틀 변경 */
        if (DataArray.peopleCount == 100) {
            binding.peopleCount.setText(R.string.p100);
        } else if (DataArray.peopleCount == 10000) {
            binding.peopleCount.setText(R.string.p10000);
        } else {
            binding.peopleCount.setText(R.string.p500000);
        }

        /* RecyclerView */
        hobbyListRCAdapter = new HobbyListRCAdapter(this, hobbyArray);
        binding.hobbyListRc.setAdapter(hobbyListRCAdapter);
        binding.hobbyListRc.setLayoutManager(new LinearLayoutManager(this));


        /* RecyclerView 정렬 */
        hobbyRCSave();


    } // onCreate 끝


    /* RecyclerView 정렬 */
    private void hobbyRCSave() {

        if (DataArray.peopleCount == 100) {
            for (int counter = 0; counter < DataArray.t100hobbyList2.size(); counter++) {
                hobbyArray.add(new HobbyListItem(DataArray.t100hobbyList2.get(counter)));
            }
            hobbyListRCAdapter.notifyDataSetChanged();
        } else if (DataArray.peopleCount == 10000) {
            for (int counter = 0; counter < DataArray.t10000hobbyList2.size(); counter++) {
                hobbyArray.add(new HobbyListItem(DataArray.t10000hobbyList2.get(counter)));
            }
            hobbyListRCAdapter.notifyDataSetChanged();
        } else {
            for (int counter = 0; counter < DataArray.t500000hobbyList2.size(); counter++) {
                hobbyArray.add(new HobbyListItem(DataArray.t500000hobbyList2.get(counter)));
            }
            hobbyListRCAdapter.notifyDataSetChanged();
        }
    }


    /* 뒤로가기 버튼 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {                                                                 // toolbar back 키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}