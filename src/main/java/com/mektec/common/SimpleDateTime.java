package com.mektec.common;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleDateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private int sec;
    private int ms;

    public SimpleDateTime(long time) {
        //截取日期
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        String yearStr = sdf.format(date);
        year = Integer.parseInt(yearStr);
        //
        sdf.applyPattern("MM");
        String monthStr = sdf.format(date);
        month = Integer.parseInt(monthStr);

        sdf.applyPattern("dd");
        String dayStr = sdf.format(date);
        day = Integer.parseInt(dayStr);

        sdf.applyPattern("HH");
        String hourStr = sdf.format(date);
        hour = Integer.parseInt(hourStr);

        sdf.applyPattern("mm");
        String minStr = sdf.format(date);
        min = Integer.parseInt(minStr);

        sdf.applyPattern("ss");
        String secStr = sdf.format(date);
        sec = Integer.parseInt(secStr);

        sdf.applyPattern("ms");
        String msStr = sdf.format(date);
        ms = Integer.parseInt(msStr);

    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getMs() {
        return ms;
    }
}
