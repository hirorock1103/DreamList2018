package com.example.user.dreamlist2018;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {


    public static String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT_SAMPLE_1 = "yyyy/MM/dd";

    /**
     * 日付変更
     * @param str
     * @param pattern1
     * @param patten2
     * @return
     */
    public static String formatStrToDate(String str, String pattern1 , String patten2){

        String formatdate = "";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern1);
        SimpleDateFormat tosdf = new SimpleDateFormat(patten2);

        try {
            Date date = sdf.parse(str);
            formatdate = tosdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatdate;

    }

    public static String formatDate(Date date , String pattern){

        String formatdate = "";
        SimpleDateFormat tosdf = new SimpleDateFormat(pattern);
        formatdate = tosdf.format(date);

        return formatdate;
    }

    /**
     * log
     */
    public static void log(String str){

        Log.i("INFO_TEST", str);

    }

    /**
     * toast
     */
    public static void toast(Context context , String str){

        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

    }

}
