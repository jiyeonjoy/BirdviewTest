package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jiyeonchoi.birdviewtest.Adapter.HobbyListRCAdapter;
import com.jiyeonchoi.birdviewtest.Data_VO.DataArray;
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
        getSupportActionBar().setTitle(R.string.hobbyListTitle);                                      // 툴바 이름 세팅

        /* 데이터 수신 */
        Intent intent = getIntent();
        // 현재 데이터 인원 수
        DataArray.peopleCount = intent.getExtras().getInt("peopleCount", 0);

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



        /* 파일 읽었는지 확인해서 이미 읽었으면 저장된 값을 불러온다. */
        if (DataArray.peopleCount == 100) {
            if(!DataArray.t100Read) {
                /* 파일 읽기 */
                readTxt();
                /* 취미 비교 */
                hobbyCompare();
            }
        } else if (DataArray.peopleCount == 10000) {
            if(!DataArray.t10000Read) {
                /* 파일 읽기 */
                readTxt();
                /* 취미 비교 */
                hobbyCompare();
            }
        } else {
            if(!DataArray.t500000Read) {
                /* 파일 읽기 */
                readTxt();
                /* 취미 비교 */
                hobbyCompare();
            }
        }




        /* test 잘불러와지나 확인!! */
        hobbyRCSave();



    }   // onCreate 끝




    /* 파일 선택해서 ArrayList 목록 저장 */
    private void readTxt() {

        /* 파일 선택 */
        if (DataArray.peopleCount == 100) {
            inputStream = getResources().openRawResource(R.raw.t100);
        } else if (DataArray.peopleCount == 10000) {
            inputStream = getResources().openRawResource(R.raw.t10000);
        } else {
            inputStream = getResources().openRawResource(R.raw.test);
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "EUC_KR"));
            boolean a = false;                                                                       // 파일 첫번째 라인 제거 하기 위한 변수
            while (true) {
                String string = bufferedReader.readLine();
                if (a) {
                    if (string != null) {
                        if (DataArray.peopleCount == 100) {
                            DataArray.t100peopleList.add(string);
                        } else if (DataArray.peopleCount == 10000) {
                            DataArray.t10000peopleList.add(string);
                        } else {
                            DataArray.t500000peopleList.add(string);
                        }
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



    /* 취미 비교 */
    private void hobbyCompare() {

        int maxMatchCount = 0;                                                                        // 가장 많이 매칭 되는 값

        /* 사람 100명일 때 */
        if(DataArray.peopleCount == 100) {

            for(int h = 0; h < 100; h++) {

                String standardPerson = DataArray.t100peopleList.get(h).replaceAll(" ", "");             // 첫번째 사람 공백 제거
                //Log.d("eeeeeeeeeeeeee111", firstPerson);
                for (int i = 0; i < 100 ; i++) {
                    if (h < i) {
                        int matchCount = 0;                                                                      // 매칭 카운트
                        ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미

                        for (int j = 0; j < 10; j++) {

                            String a = String.valueOf(standardPerson.charAt(j));                                    // firstPerson j 번째 문자열
                            //Log.d("eeeeeeeeeeeeee222", a);
                            if (DataArray.t100peopleList.get(i).contains(a)) {
                                matchCount++;
                                //Log.d("eeeeeeeeeeeeee333", matchCount+"");
                                matchHobby.add(a);
                            }
                        }


                        if (matchCount > maxMatchCount) {
                            // 기록갱신
                            maxMatchCount = matchCount;
                            Log.d("eeeeeeeeeeeeee", "기존의 목록 갱신");
                            // 기존의 커플목록, 취미목록 삭제
                            DataArray.t100hobbyList.clear();
                            DataArray.t100coupleList.clear();
                            DataArray.t100coupleList2.clear();
                            // 신규 커플목록, 취미목록 등록
                            // 알파벳 순으로 매칭 취미 정렬
                            //matchHobby.sort(String::compareToIgnoreCase);
                            Collections.sort(matchHobby);
                            String matchHobbyString = "";
                            for (int k = 0; k < matchHobby.size(); k++) {
                                matchHobbyString += matchHobby.get(k);
                            }
                            DataArray.t100hobbyList.add(matchHobbyString);
                            DataArray.t100coupleList.add(h + 1);
                            DataArray.t100coupleList2.add(i + 1);
                        } else if (matchCount == maxMatchCount && matchCount != 0) {
                            // 기존의 커플목록, 취미목록에 추가
                            // 알파벳 순으로 매칭 취미 정렬
                            Log.d("eeeeeeeeeeeeee", "기존의 목록 추가");
                            Collections.sort(matchHobby);
                            String matchHobbyString = "";
                            for (int l = 0; l < matchHobby.size(); l++) {
                                matchHobbyString += matchHobby.get(l);
                            }
                            DataArray.t100hobbyList.add(matchHobbyString);
                            DataArray.t100coupleList.add(h + 1);
                            DataArray.t100coupleList2.add(i + 1);
                        }
                    }
                }
            }

            Collections.sort(DataArray.t100hobbyList);                                                                 // 취미 리스트 알파벳 순으로 정렬
            // 중복제거
            for (int i = 0; i < DataArray.t100hobbyList.size(); i++ ) {
                if(!DataArray.t100hobbyList2.contains(DataArray.t100hobbyList.get(i))) {
                    DataArray.t100hobbyList2.add(DataArray.t100hobbyList.get(i));
                }
            }
            // t100 데이터 읽었다는 확인
            DataArray.t100Read = true;









        }   /* 사람 10000명일 때 */
        else if (DataArray.peopleCount == 10000) {


            // 1번째 사람 기준 나중에 기준 모두 for문 돌리고 복사한 peopleList2 하나씩 지우기-ArryaList 인텐트 전송되나?
            String firstPerson = DataArray.t10000peopleList.get(0).replaceAll(" ", "");             // 첫번째 사람 공백 제거
            //Log.d("eeeeeeeeeeeeee111", firstPerson);
            for (int i = 1; i < DataArray.t10000peopleList.size() ; i++) {
                int matchCount = 0;                                                                      // 매칭 카운트
                ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미

                for (int j = 0; j < 10; j++) {
                    String a = String.valueOf(firstPerson.charAt(j));                                    // firstPerson j 번째 문자열
                    //Log.d("eeeeeeeeeeeeee222", a);
                    if (DataArray.t10000peopleList.get(i).contains(a)) {
                        matchCount++;
                        //Log.d("eeeeeeeeeeeeee333", matchCount+"");
                        matchHobby.add(a);
                    }
                }

                if (matchCount > maxMatchCount) {
                    // 기록갱신
                    maxMatchCount = matchCount;
                    Log.d("eeeeeeeeeeeeee", "기존의 목록 갱신");
                    // 기존의 커플목록, 취미목록 삭제
                    DataArray.t10000hobbyList.clear();
                    DataArray.t10000coupleList.clear();
                    DataArray.t10000coupleList2.clear();
                    // 신규 커플목록, 취미목록 등록
                    // 알파벳 순으로 매칭 취미 정렬
                    //matchHobby.sort(String::compareToIgnoreCase);
                    Collections.sort(matchHobby);
                    String matchHobbyString = "";
                    for ( int k = 0; k < matchHobby.size(); k++) {
                        matchHobbyString += matchHobby.get(k);
                    }
                    DataArray.t10000hobbyList.add(matchHobbyString);
                    DataArray.t10000coupleList.add(1);
                    DataArray.t10000coupleList2.add(i+1);
                } else if (matchCount == maxMatchCount && matchCount != 0){
                    // 기존의 커플목록, 취미목록에 추가
                    // 알파벳 순으로 매칭 취미 정렬
                    Log.d("eeeeeeeeeeeeee", "기존의 목록 추가");
                    Collections.sort(matchHobby);
                    String matchHobbyString = "";
                    for ( int k = 0; k < matchHobby.size(); k++) {
                        matchHobbyString += matchHobby.get(k);
                    }
                    DataArray.t10000hobbyList.add(matchHobbyString);
                    DataArray.t10000coupleList.add(1);
                    DataArray.t10000coupleList2.add(i+1);

                }
            }

            Collections.sort(DataArray.t10000hobbyList);                                                                 // 취미 리스트 알파벳 순으로 정렬
            // 중복제거
            for (int i = 0; i < DataArray.t10000hobbyList.size(); i++ ) {
                if(!DataArray.t10000hobbyList2.contains(DataArray.t10000hobbyList.get(i))) {
                    DataArray.t10000hobbyList2.add(DataArray.t10000hobbyList.get(i));
                }
            }
            // t10000 데이터 읽었다는 확인
            DataArray.t10000Read = true;

        }   /* 사람 500000명일 때 */
        else {

            // 1번째 사람 기준 나중에 기준 모두 for문 돌리고 복사한 peopleList2 하나씩 지우기-ArryaList 인텐트 전송되나?
            String firstPerson = DataArray.t500000peopleList.get(0).replaceAll(" ", "");             // 첫번째 사람 공백 제거
            //Log.d("eeeeeeeeeeeeee111", firstPerson);
            for (int i = 1; i < DataArray.t500000peopleList.size() ; i++) {
                int matchCount = 0;                                                                      // 매칭 카운트
                ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미

                for (int j = 0; j < 10; j++) {
                    String a = String.valueOf(firstPerson.charAt(j));                                    // firstPerson j 번째 문자열
                    //Log.d("eeeeeeeeeeeeee222", a);
                    if (DataArray.t500000peopleList.get(i).contains(a)) {
                        matchCount++;
                        //Log.d("eeeeeeeeeeeeee333", matchCount+"");
                        matchHobby.add(a);
                    }
                }

                if (matchCount > maxMatchCount) {
                    // 기록갱신
                    maxMatchCount = matchCount;
                    Log.d("eeeeeeeeeeeeee", "기존의 목록 갱신");
                    // 기존의 커플목록, 취미목록 삭제
                    DataArray.t500000hobbyList.clear();
                    DataArray.t500000coupleList.clear();
                    DataArray.t500000coupleList2.clear();
                    // 신규 커플목록, 취미목록 등록
                    // 알파벳 순으로 매칭 취미 정렬
                    //matchHobby.sort(String::compareToIgnoreCase);
                    Collections.sort(matchHobby);
                    String matchHobbyString = "";
                    for ( int k = 0; k < matchHobby.size(); k++) {
                        matchHobbyString += matchHobby.get(k);
                    }
                    DataArray.t500000hobbyList.add(matchHobbyString);
                    DataArray.t500000coupleList.add(1);
                    DataArray.t500000coupleList2.add(i+1);
                } else if (matchCount == maxMatchCount && matchCount != 0){
                    // 기존의 커플목록, 취미목록에 추가
                    // 알파벳 순으로 매칭 취미 정렬
                    Log.d("eeeeeeeeeeeeee", "기존의 목록 추가");
                    Collections.sort(matchHobby);
                    String matchHobbyString = "";
                    for ( int k = 0; k < matchHobby.size(); k++) {
                        matchHobbyString += matchHobby.get(k);
                    }
                    DataArray.t500000hobbyList.add(matchHobbyString);
                    DataArray.t500000coupleList.add(1);
                    DataArray.t500000coupleList2.add(i+1);

                }
            }

            Collections.sort(DataArray.t500000hobbyList);                                                                 // 취미 리스트 알파벳 순으로 정렬
            // 중복제거
            for (int i = 0; i < DataArray.t500000hobbyList.size(); i++ ) {
                if(!DataArray.t500000hobbyList2.contains(DataArray.t500000hobbyList.get(i))) {
                    DataArray.t500000hobbyList2.add(DataArray.t500000hobbyList.get(i));
                }
            }
            // t500000 데이터 읽었다는 확인
            DataArray.t500000Read = true;
        }
    }


    // 로그찍기 성공!!! 감동!!!!
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
        switch (item.getItemId()){
            case android.R.id.home:{                                                                 // toolbar back 키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}