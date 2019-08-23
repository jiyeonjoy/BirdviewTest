package com.jiyeonchoi.birdviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jiyeonchoi.birdviewtest.Data_VO.DataArray;
import com.jiyeonchoi.birdviewtest.HobbyListActivity;
import com.jiyeonchoi.birdviewtest.R;
import com.jiyeonchoi.birdviewtest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    /* 데이터바인딩 */
    private ActivityMainBinding binding;

    /* 데이터 파일 읽기 */
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);

        binding.text100.setOnClickListener(v -> {

            if (!DataArray.t100Read) {
                /* 파일 읽기 */
                readTxt(100);
                /* 취미 비교 */
                hobbyCompare(100);
            }
            goHobbyListIntent();
        });

        binding.text10000.setOnClickListener(v -> {
            if (!DataArray.t10000Read) {
                // new ProgressTask().execute();        // 프로그레스바 시도했으나 너무 오래걸려서 포기
                /* 파일 읽기 */
                readTxt(10000);
                /* 취미 비교 */
                //hobbyCompare(10000); // 첫 번째 방법 - 2분 22초 걸림
                hobbyCompareBigData(10000);  // 두 번째 방법 - 30초 걸림
            } else {
                goHobbyListIntent();
            }

        });

        binding.text500000.setOnClickListener(v -> {
            if (!DataArray.t500000Read) {
                // new ProgressTask().execute();     // 프로그레스바 이용한 방법! - 시도했으나 너무 오래걸려서 포기
                /* 파일 읽기 */
                readTxt(500000);
                /* 취미 비교 */
                //hobbyCompare(500000); // 첫 번째 방법
                hobbyCompareBigData(500000);  // 두 번째 방법 - 혹시 앱이 죽거든 프로그레스바 이용한 방법으로 부탁드립니다!
            } else {
                goHobbyListIntent();
            }
        });


    }   // onCreate 끝


    /* 파일 읽고 ArrayList 목록 저장 */
    private void readTxt(int peopleCount) {
        DataArray.peopleCount = peopleCount;

        /* 파일 선택 */
        if (peopleCount == 100) {
            inputStream = getResources().openRawResource(R.raw.t100);
        } else if (peopleCount == 10000) {
            inputStream = getResources().openRawResource(R.raw.t10000);
        } else {
            inputStream = getResources().openRawResource(R.raw.t500000);
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "EUC_KR"));
            boolean a = false;                                                                       // 파일 첫번째 라인 제거 하기 위한 변수
            while (true) {
                String string = bufferedReader.readLine();
                if (a) {
                    if (string != null) {
                        if (peopleCount == 100) {
                            DataArray.t100peopleList.add(string);
                        } else if (peopleCount == 10000) {
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




    /* 1번째 방법 - 취미 비교 */
    private void hobbyCompare(int peopleCount) {
        int maxMatchCount = 0;                                                                       // 가장 많이 매칭 되는 값

        /* 사람 100명일 때 */
        if (peopleCount == 100) {

            for (int h = 0; h < DataArray.t100peopleList.size(); h++) {

                String standardPerson = DataArray.t100peopleList.get(h).replaceAll(" ", ""); // 기준인 사람 공백 제거
                for (int i = h + 1; i < DataArray.t100peopleList.size(); i++) {
                    int matchCount = 0;                                                                      // 매칭 카운트
                    ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미
                    matchHobby.clear();

                    for (int j = 0; j < 10; j++) {

                        String a = String.valueOf(standardPerson.charAt(j));                                 // standardPerson j 번째 문자열

                        if (DataArray.t100peopleList.get(i).contains(a)) {
                            matchCount++;
                            matchHobby.add(a);
                        }
                    }


                    if (matchCount > maxMatchCount) {
                        // 기록갱신
                        maxMatchCount = matchCount;
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

            // 중복제거 후 알파벳 순 정렬
            for (int i = 0; i < DataArray.t100hobbyList.size(); i++) {
                if (!DataArray.t100hobbyList2.contains(DataArray.t100hobbyList.get(i))) {
                    DataArray.t100hobbyList2.add(DataArray.t100hobbyList.get(i));
                }
            }
            Collections.sort(DataArray.t100hobbyList2);
            // t100 데이터 읽었다는 확인
            DataArray.t100Read = true;


        }   /* 사람 10000명일 때 */ else if (peopleCount == 10000) {
            for (int h = 0; h < DataArray.t10000peopleList.size(); h++) {
                String standardPerson = DataArray.t10000peopleList.get(h).replaceAll(" ", ""); // 기준인 사람 공백 제거
                Log.d("wait please...", h + "");
                for (int i = h + 1; i < DataArray.t10000peopleList.size(); i++) {

                    int matchCount = 0;                                                                      // 매칭 카운트
                    ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미
                    matchHobby.clear();
                    for (int j = 0; j < 10; j++) {
                        String a = String.valueOf(standardPerson.charAt(j));                                 // standardPerson j 번째 문자열

                        if (DataArray.t10000peopleList.get(i).contains(a)) {
                            matchCount++;
                            matchHobby.add(a);
                        }
                    }

                    if (matchCount > maxMatchCount) {
                        // 기록갱신
                        maxMatchCount = matchCount;
                        // 기존의 커플목록, 취미목록 삭제
                        DataArray.t10000hobbyList.clear();
                        DataArray.t10000coupleList.clear();
                        DataArray.t10000coupleList2.clear();
                        // 신규 커플목록, 취미목록 등록
                        // 알파벳 순으로 매칭 취미 정렬
                        //matchHobby.sort(String::compareToIgnoreCase);
                        Collections.sort(matchHobby);
                        String matchHobbyString = "";
                        for (int k = 0; k < matchHobby.size(); k++) {
                            matchHobbyString += matchHobby.get(k);
                        }
                        DataArray.t10000hobbyList.add(matchHobbyString);
                        DataArray.t10000coupleList.add(h + 1);
                        DataArray.t10000coupleList2.add(i + 1);
                    } else if (matchCount == maxMatchCount && matchCount != 0) {
                        // 기존의 커플목록, 취미목록에 추가
                        // 알파벳 순으로 매칭 취미 정렬
                        Collections.sort(matchHobby);
                        String matchHobbyString = "";
                        for (int l = 0; l < matchHobby.size(); l++) {
                            matchHobbyString += matchHobby.get(l);
                        }
                        DataArray.t10000hobbyList.add(matchHobbyString);
                        DataArray.t10000coupleList.add(h + 1);
                        DataArray.t10000coupleList2.add(i + 1);

                    }
                }
            }
            // 중복제거 후 알파벳 순 정렬
            for (int i = 0; i < DataArray.t10000hobbyList.size(); i++) {
                if (!DataArray.t10000hobbyList2.contains(DataArray.t10000hobbyList.get(i))) {
                    DataArray.t10000hobbyList2.add(DataArray.t10000hobbyList.get(i));
                }
            }
            Collections.sort(DataArray.t10000hobbyList2);
            // t10000 데이터 읽었다는 확인
            DataArray.t10000Read = true;



        }   /* 사람 500000명일 때 */ else {

            for (int h = 0; h < DataArray.t500000peopleList.size(); h++) {
                String standardPerson = DataArray.t500000peopleList.get(h).replaceAll(" ", ""); // 기준인 사람 공백 제거
                Log.d("wait please...", h + "");
                for (int i = h + 1; i < DataArray.t500000peopleList.size(); i++) {
                    int matchCount = 0;                                                                      // 매칭 카운트
                    ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미
                    matchHobby.clear();
                    for (int j = 0; j < 10; j++) {
                        String a = String.valueOf(standardPerson.charAt(j));                                 // standardPerson j 번째 문자열

                        if (DataArray.t500000peopleList.get(i).contains(a)) {
                            matchCount++;
                            matchHobby.add(a);
                        }
                    }

                    if (matchCount > maxMatchCount) {

                        // 기록갱신
                        maxMatchCount = matchCount;

                        // 기존의 커플목록, 취미목록 삭제
                        DataArray.t500000hobbyList.clear();
                        DataArray.t500000coupleList.clear();
                        DataArray.t500000coupleList2.clear();

                        // 신규 커플목록, 취미목록 등록
                        // 알파벳 순으로 매칭 취미 정렬
                        //matchHobby.sort(String::compareToIgnoreCase);
                        Collections.sort(matchHobby);
                        String matchHobbyString = "";
                        for (int k = 0; k < matchHobby.size(); k++) {
                            matchHobbyString += matchHobby.get(k);
                        }
                        DataArray.t500000hobbyList.add(matchHobbyString);
                        DataArray.t500000coupleList.add(h + 1);
                        DataArray.t500000coupleList2.add(i + 1);


                    } else if (matchCount == maxMatchCount && matchCount != 0) {
                        // 기존의 커플목록, 취미목록에 추가
                        // 알파벳 순으로 매칭 취미 정렬
                        Collections.sort(matchHobby);
                        String matchHobbyString = "";
                        for (int l = 0; l < matchHobby.size(); l++) {
                            matchHobbyString += matchHobby.get(l);
                        }
                        DataArray.t500000hobbyList.add(matchHobbyString);
                        DataArray.t500000coupleList.add(h + 1);
                        DataArray.t500000coupleList2.add(i + 1);

                    }

                }
            }

            // 중복제거 후 알파벳 순 정렬
            for (int i = 0; i < DataArray.t500000hobbyList.size(); i++) {
                if (!DataArray.t500000hobbyList2.contains(DataArray.t500000hobbyList.get(i))) {
                    DataArray.t500000hobbyList2.add(DataArray.t500000hobbyList.get(i));
                }
            }
            Collections.sort(DataArray.t500000hobbyList2);
            // t500000 데이터 읽었다는 확인
            DataArray.t500000Read = true;
        }
    }








    /* 2번째 방법 - Big Data 취미 비교 */
    private void hobbyCompareBigData(int peopleCoutn) {

        /* 사람 10000명일 때 */
        if(peopleCoutn == 10000) {
            // 최대 매칭개수 10개부터 시작 10개있으면 더이상 반복하지 않음!
            boolean check = true;
            // 10개부터 차근차근 내려감!
            int matchMaxHobbyCount = 10;
            while (check) {
                // 기준 공백제거
                for (int h = 0; h < DataArray.t10000peopleList.size(); h++) {
                    String standardPerson = DataArray.t10000peopleList.get(h).replaceAll(" ", ""); // 기준인 사람 공백 제거
                    Log.d("wait please...", h + "");
                    for (int i = h + 1; i < DataArray.t10000peopleList.size(); i++) {
                        int matchCount = 0;                                                                      // 매칭 카운트
                        int unmatchCount = 0;                                                                    // 언매칭 카운트
                        ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미
                        matchHobby.clear();

                        for (int j = 0; j < 10; j++) {
                            String a = String.valueOf(standardPerson.charAt(j));                                 // standardPerson j 번째 문자열
                            if (DataArray.t10000peopleList.get(i).contains(a)) {
                                matchCount++;
                                matchHobby.add(a);
                            } else {
                                unmatchCount++;

                            }
                            if (unmatchCount > 10 - matchMaxHobbyCount) {
                                break;
                            }
                        }

                        if (matchCount == matchMaxHobbyCount) {
                            // 알파벳 순으로 매칭 취미 정렬
                            Collections.sort(matchHobby);
                            String matchHobbyString = "";
                            for (int l = 0; l < matchHobby.size(); l++) {
                                matchHobbyString += matchHobby.get(l);
                            }
                            DataArray.t10000hobbyList.add(matchHobbyString);
                            DataArray.t10000coupleList.add(h + 1);
                            DataArray.t10000coupleList2.add(i + 1);
                            check = false;
                        }
                    }
                }
                matchMaxHobbyCount--;
            }

            // 취미 리스트 중복제거 후 알파벳 순 정렬
            for (int i = 0; i < DataArray.t10000hobbyList.size(); i++) {
                if (!DataArray.t10000hobbyList2.contains(DataArray.t10000hobbyList.get(i))) {
                    DataArray.t10000hobbyList2.add(DataArray.t10000hobbyList.get(i));
                }
            }
            Collections.sort(DataArray.t10000hobbyList2);
            // t10000 데이터 읽었다는 확인
            DataArray.t10000Read = true;



        } else      /* 사람 500000명일 때 */    {

            // 최대 매칭개수 10개부터 시작 10개있으면 더이상 반복하지 않음!
            boolean check = true;
            // 10개부터 차근차근 내려감!
            int matchMaxHobbyCount = 10;
            while (check) {
                // 기준 공백제거
                for (int h = 0; h < DataArray.t500000peopleList.size(); h++) {
                    String standardPerson = DataArray.t500000peopleList.get(h).replaceAll(" ", ""); // 기준인 사람 공백 제거
                    Log.d("wait please...", h + "");
                    for (int i = h + 1; i < DataArray.t500000peopleList.size(); i++) {
                        int matchCount = 0;                                                                      // 매칭 카운트
                        int unmatchCount = 0;                                                                    // 언매칭 카운트
                        ArrayList<String> matchHobby = new ArrayList<>();                                        // 매칭 취미
                        matchHobby.clear();

                        for (int j = 0; j < 10; j++) {
                            String a = String.valueOf(standardPerson.charAt(j));                                 // standardPerson j 번째 문자열
                            if (DataArray.t500000peopleList.get(i).contains(a)) {
                                matchCount++;
                                matchHobby.add(a);
                            } else {
                                unmatchCount++;

                            }
                            if (unmatchCount > 10 - matchMaxHobbyCount) {
                                break;
                            }
                        }

                        if (matchCount == matchMaxHobbyCount) {
                            // 알파벳 순으로 매칭 취미 정렬
                            Collections.sort(matchHobby);
                            String matchHobbyString = "";
                            for (int l = 0; l < matchHobby.size(); l++) {
                                matchHobbyString += matchHobby.get(l);
                            }
                            DataArray.t500000hobbyList.add(matchHobbyString);
                            DataArray.t500000coupleList.add(h + 1);
                            DataArray.t500000coupleList2.add(i + 1);
                            check = false;
                        }
                    }
                }
                matchMaxHobbyCount--;
            }

            // 취미 리스트 중복제거 후 알파벳 순 정렬
            for (int i = 0; i < DataArray.t500000hobbyList.size(); i++) {
                if (!DataArray.t500000hobbyList2.contains(DataArray.t500000hobbyList.get(i))) {
                    DataArray.t500000hobbyList2.add(DataArray.t500000hobbyList.get(i));
                }
            }
            Collections.sort(DataArray.t500000hobbyList2);
            // t500000 데이터 읽었다는 확인
            DataArray.t500000Read = true;
        }
    }







    /* 매칭되는 취미 리스트 액티비티로 이동 */
    public void goHobbyListIntent() {
        Intent go_hobby_list = new Intent(getApplication().getApplicationContext(), HobbyListActivity.class);
        startActivity(go_hobby_list);

    }





    /* 프로그레스바 - 시도해 보았으나 너무 시간이 오래 걸려서 포기 ... */
    private class ProgressTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            if (DataArray.peopleCount == 10000) {
                /* 파일 읽기 */
                readTxt(10000);
                /* 취미 비교 */
                //hobbyCompare(10000); // 첫 번째 방법
                hobbyCompareBigData(10000);  // 두 번째 방법
            } else {
                /* 파일 읽기 */
                readTxt(500000);
                /* 취미 비교 */
                //hobbyCompare(500000);  // 첫 번째 방법
                hobbyCompareBigData(500000);   // 두 번째 방법
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            binding.progressBar.setVisibility(View.GONE);
            goHobbyListIntent();

        }
    }
}