package soul.listener.com.humiture.util;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

import soul.listener.com.humiture.a_model.TemperatureModel;

/**
 * Created by kys_31 on 2017/12/19.
 */

public class ValueFormatterUtil {

    public static IValueFormatter getTemperatureFormat() {
        return new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf(entry.getY()) + "℃";
            }
        };
    }

    public static IAxisValueFormatter getXAxisValueFormat(final List<TemperatureModel> list) {
        return new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = Integer.valueOf(String.valueOf(value).substring(0, String.valueOf(value).indexOf(".")));
                if (Constants.num == 2){
                    i += 12;
                }
                String xData;
                if (i >= list.size()){
                    xData = " ";
                }else {
                     xData = String.valueOf(list.get(i).getCurrentTime().substring(0, 5));
                }
                return xData;
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };
    }

    public static IAxisValueFormatter getDealX(final List<TemperatureModel> list){
        return new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = Integer.valueOf(String.valueOf(value).substring(0, String.valueOf(value).indexOf(".")));
                Log.e("看看",i+"");
                i+=(Constants.num-1)*12;

                String xData;
                if (i >= list.size()){
                    xData = " ";
                }else {
                    xData = list.get(i).getCurrentDate().substring(5);
                }
                Log.e("变化",xData);
                return xData;
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };
    }

    public static IAxisValueFormatter getXAxisTimePoint() {
        return new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = Integer.valueOf(String.valueOf(value).substring(0, String.valueOf(value).indexOf(".")));
                if (i == 12) i -= 1;
                if (i > 12){
                    return " ";
                }
                String reValue = null;
                if (Constants.num == 1) {
                    switch (i) {
                        case 0:
                            reValue = "00";
                            break;
                        case 1:
                            reValue = "01";
                            break;
                        case 2:
                            reValue = "02";
                            break;
                        case 3:
                            reValue = "03";
                            break;
                        case 4:
                            reValue = "04";
                            break;
                        case 5:
                            reValue = "05";
                            break;
                        case 6:
                            reValue = "06";
                            break;
                        case 7:
                            reValue = "07";
                            break;
                        case 8:
                            reValue = "08";
                            break;
                        case 9:
                            reValue = "09";
                            break;
                        case 10:
                            reValue = "10";
                            break;
                        case 11:
                            reValue = "11";
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            reValue = "12";
                            break;
                        case 1:
                            reValue = "13";
                            break;
                        case 2:
                            reValue = "14";
                            break;
                        case 3:
                            reValue = "15";
                            break;
                        case 4:
                            reValue = "16";
                            break;
                        case 5:
                            reValue = "17";
                            break;
                        case 6:
                            reValue = "18";
                            break;
                        case 7:
                            reValue = "19";
                            break;
                        case 8:
                            reValue = "20";
                            break;
                        case 9:
                            reValue = "21";
                            break;
                        case 10:
                            reValue = "22";
                            break;
                        case 11:
                            reValue = "23";
                            break;
                        default:
                            break;
                    }
                }
                    return reValue;
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };
    }


}
