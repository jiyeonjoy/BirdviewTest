package com.jiyeonchoi.birdviewtest.Data_VO;

public class CoupleListItem {

    String coupleNumber;
    String firstCouple;
    String secondCouple;

    public String getCoupleNumber() {
        return coupleNumber;
    }

    public void setCoupleNumber(String coupleNumber) {
        this.coupleNumber = coupleNumber;
    }

    public String getFirstCouple() {
        return firstCouple;
    }

    public void setFirstCouple(String firstCouple) {
        this.firstCouple = firstCouple;
    }

    public String getSecondCouple() {
        return secondCouple;
    }

    public void setSecondCouple(String secondCouple) {
        this.secondCouple = secondCouple;
    }

    public CoupleListItem(String coupleNumber, String firstCouple, String secondCouple) {
        this.coupleNumber = coupleNumber;
        this.firstCouple = firstCouple;
        this.secondCouple = secondCouple;
    }

}
