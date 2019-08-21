package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.Adapter.HobbyListRCAdapter;
import com.jiyeonchoi.birdviewtest.ListItem.HobbyListItem;
import com.jiyeonchoi.birdviewtest.databinding.ActivityHobbyListBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HobbyListActivity extends AppCompatActivity {

    private ActivityHobbyListBinding binding;

    int peopleCount;

    ArrayList<String> coupleList;

    InputStream inputStream;

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

        /* RecyclerView */
        hobbyListRCAdapter = new HobbyListRCAdapter(this, hobbyArray);
        binding.hobbyListRc.setAdapter(hobbyListRCAdapter);
        binding.hobbyListRc.setLayoutManager(new LinearLayoutManager(this));


        /* 파일 읽기 */
        readTxt();











        /* test 잘불러와지나 확인!! */
        hobbyRCSave();

    }   // onCreate 끝





    /* 파일 읽기 */
    private void readTxt() {
        if (peopleCount == 100) {
            inputStream = getResources().openRawResource(R.raw.t100);
        } else if (peopleCount == 10000) {
            inputStream = getResources().openRawResource(R.raw.t10000);
        } else {
            inputStream = getResources().openRawResource(R.raw.t500000);
        }

        coupleList = new ArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "EUC_KR"));
            boolean a = false;                                                                       // 파일 첫번째 라인 제거 하기 위한 변수
            while (true) {
                String string = bufferedReader.readLine();
                if (a) {
                    if (string != null) {
                        coupleList.add(string);
                    } else {
                        break;
                    }
                }
                a = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



















    // 로그찍기 성공!!! 감동!!!!
    private void hobbyRCSave() {
        if(peopleCount == 100 | peopleCount == 10000) {
            for (int counter = 0; counter < coupleList.size(); counter++) {
                //System.out.println(coupleList.get(counter));
                //Log.d("eeeeeeeeeeeeee", coupleList.get(counter));

                hobbyArray.add(new HobbyListItem(coupleList.get(counter)));
                hobbyListRCAdapter.notifyDataSetChanged();
        }
        }
    }




//    /* 파일 읽기 */
//    private String readTxt() {
//        String data = null;
//        InputStream inputStream = getResources().openRawResource(R.raw.test);
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//        int i;
//        try {
//            i = inputStream.read();
//            while (i != -1) {
//                byteArrayOutputStream.write(i);
//                i = inputStream.read();
//            }
//
//            data = new String(byteArrayOutputStream.toByteArray(),"MS949");
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }



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
