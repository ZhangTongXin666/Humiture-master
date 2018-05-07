package soul.listener.com.humiture.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kys_31 on 2017/12/16.
 */

public class CalculateUtil {
    private List<String> mZeroList = new ArrayList<>();
    private List<String> mOneList = new ArrayList<>();
    private List<String> mTwoList = new ArrayList<>();
    private List<String> mThreeList = new ArrayList<>();
    private List<String> mFourList = new ArrayList<>();
    private List<String> mFiveList = new ArrayList<>();
    private List<String> mSixList = new ArrayList<>();
    private List<String> mSenvenList = new ArrayList<>();
    private List<String> mEightList = new ArrayList<>();
    private List<String> mNineList = new ArrayList<>();
    private List<String> mTenList = new ArrayList<>();
    private List<String> mElevenList = new ArrayList<>();
    private List<String> mTwelveList = new ArrayList<>();
    private List<String> mThirteenList = new ArrayList<>();
    private List<String> mFourteenList = new ArrayList<>();
    private List<String> mFiveteenList = new ArrayList<>();
    private List<String> mSixteenList = new ArrayList<>();
    private List<String> mSenventeenList = new ArrayList<>();
    private List<String> mEighteenList = new ArrayList<>();
    private List<String> mNineteenList = new ArrayList<>();
    private List<String> mTwetryList = new ArrayList<>();
    private List<String> mTwetryOneList = new ArrayList<>();
    private List<String> mTwetryTwoList = new ArrayList<>();
    private List<String> mTwetryThreeList = new ArrayList<>();
    public List<String> mMaxValueList = new ArrayList<>();
    public List<String> mMinValueList = new ArrayList<>();
    public List<String> mAvgValueList = new ArrayList<>();


    private CalculateUtil(){}
    private static class InitCalculate{
        static final CalculateUtil CALCULATE_UTIL = new CalculateUtil();
    }
    public static CalculateUtil getInstance(){
        return InitCalculate.CALCULATE_UTIL;
    }

    public void startCalculator(String time, String data, boolean end){
        if (data.equals("0")){
            return;
        }
        switch (time){
            case "00":
                mZeroList.add(data);
                break;
            case "01":
                mOneList.add(data);
                break;
            case "02":
                mTwoList.add(data);
                break;
            case "03":
                mThreeList.add(data);
                break;
            case "04":
                mFourList.add(data);
                break;
            case "05":
                mFiveList.add(data);
                break;
            case "06":
                mSixList.add(data);
                break;
            case "07":
                mSenvenList.add(data);
                break;
            case "08":
                mEightList.add(data);
                break;
            case "09":
                mNineList.add(data);
                break;
            case "10":
                mTenList.add(data);
                break;
            case "11":
                mElevenList.add(data);
                break;
            case "12":
                mTwelveList.add(data);
                break;
            case "13":
                mThirteenList.add(data);
                break;
            case "14":
                mFourteenList.add(data);
                break;
            case "15":
                mFiveteenList.add(data);
                break;
            case "16":
                mSixteenList.add(data);
                break;
            case "17":
                mSenventeenList.add(data);
                break;
            case "18":
                mEighteenList.add(data);
                break;
            case "19":
                mNineteenList.add(data);
                break;
            case "20":
                mTwetryList.add(data);
                break;
            case "21":
                mTwetryOneList.add(data);
                break;
            case "22":
                mTwetryTwoList.add(data);
                break;
            case "23":
                mTwetryThreeList.add(data);
                break;
                default:break;
        }
        if (end){
            getResultValueList(mOneList);
            getResultValueList(mTwoList);
            getResultValueList(mThreeList);
            getResultValueList(mFourList);
            getResultValueList(mFiveList);
            getResultValueList(mSixList);
            getResultValueList(mSenvenList);
            getResultValueList(mEightList);
            getResultValueList(mNineList);
            getResultValueList(mTenList);
            getResultValueList(mElevenList);
            getResultValueList(mTwelveList);
            getResultValueList(mThirteenList);
            getResultValueList(mFourteenList);
            getResultValueList(mFiveteenList);
            getResultValueList(mSixteenList);
            getResultValueList(mSenventeenList);
            getResultValueList(mEighteenList);
            getResultValueList(mNineteenList);
            getResultValueList(mTwetryList);
            getResultValueList(mTwetryOneList);
            getResultValueList(mTwetryTwoList);
            getResultValueList(mTwetryThreeList);

        }
    }

    private void getResultValueList(List<String> list){
        int count = list.size();
        if (count != 0){
            double max = 0;
            for (int i = 0; i < count; i++) {
                double preValue = Double.valueOf(list.get(i));
                if (preValue > max){
                    max = preValue;
                }
            }

            double min = 100;
            for (int i = 0; i < count; i++) {
                double preValue = Double.valueOf(list.get(i));
                if (preValue < min){
                    min = preValue;
                }
            }

            double avg = 0;
            for (int i = 0; i < count; i++) {
                avg += Double.valueOf(list.get(i));
            }
            avg /= count;
            mMaxValueList.add(String.valueOf(max));
            mMinValueList.add(String.valueOf(min));
            mAvgValueList.add(String.valueOf(avg));
        }
    }

}
