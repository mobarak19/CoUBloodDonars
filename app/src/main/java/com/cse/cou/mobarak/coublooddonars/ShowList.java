package com.cse.cou.mobarak.coublooddonars;

/**
 * Created by Mobarak on 5/1/2017.
 */

public class ShowList {
    String name,blood,last_donation;

    public ShowList() {
    }
    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_donation() {
        return last_donation;
    }

    public void setLast_donation(String last_donation) {
        this.last_donation = last_donation;
    }

    public ShowList(String name, String blood, String last_donation) {

        this.name = name;
        this.blood = blood;
        this.last_donation = last_donation;
    }
}
