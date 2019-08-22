package com.jiyeonchoi.birdviewtest.Data_VO;

import java.util.ArrayList;

public class DataArray {

    public static boolean t100Read = false;                                                          // t100 데이터 읽었는지 확인 값
    public static boolean t10000Read = false;                                                        // t10000 데이터 읽었는지 확인 값
    public static boolean t500000Read = false;                                                       // t500000 데이터 읽었는지 확인 값

    public static int peopleCount;                                                                  // 현재 보고 있는 데이터 사람 수

    public static ArrayList<String> t100peopleList = new ArrayList<>();                             // t100 사람 목록
    public static ArrayList<String> t100peopleList2 = new ArrayList<>();                            // t100 사람 목록 복사
    public static ArrayList<String> t100coupleList = new ArrayList<>();                             // t100 매칭 커플 목록
    public static ArrayList<String> t100hobbyList = new ArrayList<>();                              // t100 매칭 취미 목록
    public static ArrayList<String> t100hobbyList2 = new ArrayList<>();                             // t100 매칭 취미 목록 복사

    public static ArrayList<String> t10000peopleList = new ArrayList<>();                           // t10000 사람 목록
    public static ArrayList<String> t10000peopleList2 = new ArrayList<>();                          // t10000 사람 목록 복사
    public static ArrayList<String> t10000coupleList = new ArrayList<>();                           // t10000 매칭 커플 목록
    public static ArrayList<String> t10000hobbyList = new ArrayList<>();                            // t10000 매칭 취미 목록
    public static ArrayList<String> t10000hobbyList2 = new ArrayList<>();                           // t10000 매칭 취미 목록 복사

    public static ArrayList<String> t500000peopleList = new ArrayList<>();                          // t500000 사람 목록
    public static ArrayList<String> t500000peopleList2 = new ArrayList<>();                         // t500000 사람 목록 복사
    public static ArrayList<String> t500000coupleList = new ArrayList<>();                          // t500000 매칭 커플 목록
    public static ArrayList<String> t500000hobbyList = new ArrayList<>();                           // t500000 매칭 취미 목록
    public static ArrayList<String> t500000hobbyList2 = new ArrayList<>();                          // t500000 매칭 취미 목록 복사

}
