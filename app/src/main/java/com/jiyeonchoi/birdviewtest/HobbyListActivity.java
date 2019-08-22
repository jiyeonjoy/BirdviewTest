package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.Adapter.HobbyListRCAdapter;
import com.jiyeonchoi.birdviewtest.Data_VO.HobbyListItem;
import com.jiyeonchoi.birdviewtest.databinding.ActivityHobbyListBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class HobbyListActivity extends AppCompatActivity {

    /* 데이터바인딩 */
    private ActivityHobbyListBinding binding;

    /* 데이터 파일 사람 수 */
    int peopleCount;

    /* 데이터 파일 사람 목록 */
    ArrayList<String> peopleList;
    ArrayList<String> peopleList2;                                                                   // 복사
    ArrayList<String> coupleList;
    ArrayList<String> hobbyList;
    ArrayList<String> hobbyList2;

    /* 데이터 파일 읽기 */
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




        /* 취미 비교 */
        hobbyCompare();


















        /* test 잘불러와지나 확인!! */
        hobbyRCSave();

    }   // onCreate 끝






    /* 파일 선택해서 ArrayList 목록 저장 */
    private void readTxt() {

        /* 파일 선택 */
        if (peopleCount == 100) {
            inputStream = getResources().openRawResource(R.raw.t100);
        } else if (peopleCount == 10000) {
            inputStream = getResources().openRawResource(R.raw.t10000);
        } else {
            inputStream = getResources().openRawResource(R.raw.test);
        }

        peopleList = new ArrayList();
        peopleList2 = new ArrayList();
        coupleList = new ArrayList<>();
        hobbyList = new ArrayList<>();
        hobbyList2 = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "EUC_KR"));
            boolean a = false;                                                                       // 파일 첫번째 라인 제거 하기 위한 변수
            while (true) {
                String string = bufferedReader.readLine();
                if (a) {
                    if (string != null) {
                        peopleList.add(string);
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


    private void hobbyCompare() {

        peopleList2 = peopleList;                                                                    // 복사 의미가 있나?
        int maxMatchCout = 0;                                                                        // 가장 많이 매칭 되는 값

        // 1번째 사람 기준 나중에 기준 모두 for문 돌리고 복사한 peopleList2 하나씩 지우기-ArryaList 인텐트 전송되나?
        String firstPerson = peopleList.get(0).replaceAll(" ", "");             // 첫번째 사람 공백 제거
        //Log.d("eeeeeeeeeeeeee111", firstPerson);
        for (int i = 1; i < peopleList.size() ; i++) {
            int matchCount = 0;                                                                      // 매칭 카운트
            ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미

            for (int j = 0; j < 10; j++) {
                String a = String.valueOf(firstPerson.charAt(j));                                    // firstPerson j 번째 문자열
                //Log.d("eeeeeeeeeeeeee222", a);
                if (peopleList.get(i).contains(a)) {
                    matchCount++;
                    //Log.d("eeeeeeeeeeeeee333", matchCount+"");
                    matchHobby.add(a);
                }
            }

            if (matchCount > maxMatchCout) {
                // 기록갱신
                maxMatchCout = matchCount;
                Log.d("eeeeeeeeeeeeee", "기존의 목록 갱신");
                // 기존의 커플목록, 취미목록 삭제
                hobbyList.clear();
                coupleList.clear();
                // 신규 커플목록, 취미목록 등록
                // 알파벳 순으로 매칭 취미 정렬
                //matchHobby.sort(String::compareToIgnoreCase);
                Collections.sort(matchHobby);
                String matchHobbyString = "";
                for ( int k = 0; k < matchHobby.size(); k++) {
                    matchHobbyString += matchHobby.get(k);
                }
                hobbyList.add(matchHobbyString);
                coupleList.add("1 - "+i+1);
            } else if (matchCount == maxMatchCout && matchCount != 0){
                // 기존의 커플목록, 취미목록에 추가
                // 알파벳 순으로 매칭 취미 정렬
                Log.d("eeeeeeeeeeeeee", "기존의 목록 추가");
                Collections.sort(matchHobby);
                String matchHobbyString = "";
                for ( int k = 0; k < matchHobby.size(); k++) {
                    matchHobbyString += matchHobby.get(k);
                }
                hobbyList.add(matchHobbyString);
                coupleList.add("1 - "+i+1);

            }
        }
    }
















    // 로그찍기 성공!!! 감동!!!!
    private void hobbyRCSave() {
        Collections.sort(hobbyList);                                                                 // 취미 리스트 알파벳 순으로 정렬
        // 중복제거
        for (int i = 0; i < hobbyList.size(); i++ ) {
            if(!hobbyList2.contains(hobbyList.get(i))) {
                hobbyList2.add(hobbyList.get(i));
            }
        }
        for (int counter = 0; counter < hobbyList2.size(); counter++) {
            hobbyArray.add(new HobbyListItem(hobbyList2.get(counter)));
        }
        hobbyListRCAdapter.notifyDataSetChanged();
    }



    /* 뒤로가기 버튼 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{                                                                 // toolbar back 키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}