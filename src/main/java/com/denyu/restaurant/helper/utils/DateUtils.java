package com.denyu.restaurant.helper.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

    public static Timestamp getTimeSql(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
