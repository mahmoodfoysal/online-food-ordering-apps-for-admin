package com.example.dreamland.foodlover;

import java.util.Date;

public class clatMassage {
    private String massageText;
    private String massageUser;
    private long massageTime;

    public clatMassage(String massageText, String massageUser) {
        this.massageText = massageText;
        this.massageUser = massageUser;
        massageTime=new Date().getTime();
    }

    public clatMassage() {

    }

    public String getMassageText() {
        return massageText;
    }

    public void setMassageText(String massageText) {
        this.massageText = massageText;
    }

    public String getMassageUser() {
        return massageUser;
    }

    public void setMassageUser(String massageUser) {
        this.massageUser = massageUser;
    }

    public long getMassageTime() {
        return massageTime;
    }

    public void setMassageTime(long massageTime) {
        this.massageTime = massageTime;
    }
}
