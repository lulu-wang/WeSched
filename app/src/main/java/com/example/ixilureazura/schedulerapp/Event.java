package com.example.ixilureazura.schedulerapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ixilureazura on 3/3/18.
 */

public class Event {

    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String apm;
    private String name;
    private String desc;
    private Date dateTime;
    private int remindTime;
    private ArrayList<String> phoneNumberArray;

    private String hasMsged;

    public Event(String year, String month, String day, String hour, String minute, String apm, String name, String desc, int remindTime) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.apm = apm;
        this.name = name;
        this.desc = desc;
        this.remindTime = remindTime;
        this.hasMsged = "false";

        dateTime = new Date();
        String fullDate = this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute;
        SimpleDateFormat origFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

        try {
            dateTime = origFormat.parse(fullDate);
        } catch (ParseException e) {
            System.out.println("Parsing date failed!");
        }

    }

    public Event(String year, String month, String day, String hour, String minute, String apm, String name, int remindTime) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.apm = apm;
        this.name = name;
        this.remindTime = remindTime;
        this.hasMsged = "false";

        dateTime = new Date();
        String fullDate = this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute;
        SimpleDateFormat origFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

        try {
            dateTime = origFormat.parse(fullDate);
        } catch (ParseException e) {
            System.out.println("Parsing date failed!");
        }

    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public String getDateString() {
        return this.month + "-" + this.day + "-" + this.year;
    }

    public String getTimeString() {
        return this.hour + " : " + this.minute;
    }

    public String getAPM() {
        return this.apm;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public ArrayList<String> getPhoneArray() {
        return this.phoneNumberArray;
    }

    public void setPhoneArray(ArrayList<String> pNArray) {
        this.phoneNumberArray = pNArray;
    }

    public String getHasMsged() {
        return this.hasMsged;
    }

    public void setHasMsged(String tF) {
        this.hasMsged = tF;
    }

    public int getRemindTime() {
        return this.remindTime;
    }

}