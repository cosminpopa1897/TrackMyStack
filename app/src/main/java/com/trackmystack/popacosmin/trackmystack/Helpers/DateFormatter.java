package com.trackmystack.popacosmin.trackmystack.Helpers;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cosmin on 28-Apr-18.
 */

public class DateFormatter {
    public static Date formatDate(String dateString){
        Calendar calendar = Calendar.getInstance();
        String[] parts = dateString.split("-");
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parts[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(parts[1]) -1 );
        calendar.set(Calendar.YEAR, Integer.parseInt(parts[2]));
        return calendar.getTime();
    }

}
