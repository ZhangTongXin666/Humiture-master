package soul.listener.com.humiture.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kys_31 on 2017/12/2.
 */

public class TimeUtil {

    public static String getSystemTime(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static void selectDate(Activity activity, final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String month = String.valueOf((i1+1));
                String day = String.valueOf(i2);
                if ((i1 + 1) < 10){
                    month = "0" + month;
                }if (i2 < 10){
                    day = "0" + day;
                }
                textView.setText(String.valueOf(i)+"-"+month+"-"+day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public static String changeDay(int dayNum, String day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = simpleDateFormat.parse(day);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, dayNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(calendar.getTime());
    }

}
