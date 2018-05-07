package soul.listener.com.humiture.view;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import soul.listener.com.humiture.R;
import soul.listener.com.humiture.util.MyApplication;
import soul.listener.com.humiture.util.TimeUtil;

/**
 * Created by kys_31 on 2017/12/19.
 * 折线图
 */

public class BrokenLineView {

    private String date = TimeUtil.getSystemTime();
    private int xAnimateTime = 1500;
    private int yAnimateTime = 1500;
    private LineChart mLineChart;

    private float xAxisMaximum = 11f;
    private float xAxisMinimum = 0f;
    private float xAxisTextSize = 12f;
    private int xAxisLabelCount = 12;
    private int xAxisTextColor = Color.WHITE;

    private float yLeftAxisMaximum = 100f;
    private float yLeftAxisMinimum = 0f;
    private float yLeftAxisTextSize = 12f;
    private int yLeftAxisLabelCount = 6;
    private int yLeftAxisTextColor = Color.WHITE;

    private float yRightAxisMaximum = 42f;
    private float yRightAxisMinimum = 0f;
    private float yRightAxisTextSize = 12f;
    private int yRightAxisLabelCount = 6;
    private int yRightAxisTextColor = Color.WHITE;

    public BrokenLineView(LineChart mLineChart, Context context){
        if (mLineChart != null){
            this.mLineChart = mLineChart;
        }else {
            mLineChart = new LineChart(context);
        }
        mLineChart.setEnabled(true);
        mLineChart.setTouchEnabled(true);
        MyMarkView myMarkView = new MyMarkView(context, R.layout.view_pop_selevt_value);
        mLineChart.setMarker(myMarkView);//设置标记位
        mLineChart.setDragEnabled(true);//折线图可拖拽
        mLineChart.setDragDecelerationFrictionCoef(0.9f);//设置拖拽时 减速摩擦力。值越大 停下来的越慢
        mLineChart.setScaleEnabled(true);//折线图可缩放
        mLineChart.setDrawGridBackground(false); // 是否画出网格型背景
        mLineChart.setHighlightPerDragEnabled(true);//设置拖拽时高亮
        mLineChart.setPinchZoom(true);
        mLineChart.setBackgroundColor(Color.BLACK);
        mLineChart.animateXY(xAnimateTime, yAnimateTime); //初始折线图时x,y轴同时出现动画效果。
    }

    public void showXAxisLine(IAxisValueFormatter iAxisValueFormatter){
        XAxis mXAxis = mLineChart.getXAxis();
        mXAxis.setDrawAxisLine(true);
        mXAxis.setDrawGridLines(false);
        mXAxis.setTextColor(xAxisTextColor);
        mXAxis.setLabelCount(xAxisLabelCount, true);
        mXAxis.setTextSize(xAxisTextSize);
        mXAxis.setAxisMaximum(xAxisMaximum);
        mXAxis.setAxisMinimum(xAxisMinimum);
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setValueFormatter(iAxisValueFormatter);
    }

    public void showLeftYAxisLine(){
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(yLeftAxisTextColor);
        leftAxis.setTextSize(yLeftAxisTextSize);
        leftAxis.setAxisMaximum(yLeftAxisMaximum);
        leftAxis.setAxisMinimum(yLeftAxisMinimum);
        leftAxis.setLabelCount(yLeftAxisLabelCount, true);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setGranularityEnabled(false);
    }

    public void showRightYAxisLine(){
        YAxis leftAxis = mLineChart.getAxisRight();
        leftAxis.setTextColor(yRightAxisTextColor);
        leftAxis.setTextSize(yRightAxisTextSize);
        leftAxis.setAxisMaximum(yRightAxisMaximum);
        leftAxis.setAxisMinimum(yRightAxisMinimum);
        leftAxis.setLabelCount(yRightAxisLabelCount, true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setGranularityEnabled(false);
    }

    public void showLegend(){
        Legend l = mLineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setFormToTextSpace(12f);
        l.setDrawInside(false);
    }
    public  LineDataSet getLineDataSet(List<Entry> list, int strID, YAxis.AxisDependency dependency, int color, IValueFormatter valueFormatter) {
        LineDataSet set;
        set = new LineDataSet(list, MyApplication.getContext().getResources().getString(strID));
        set.setAxisDependency(dependency);
        set.isDrawValuesEnabled();
        set.setColor(color);
        set.setLineWidth(2f);
        set.setCircleColor(Color.WHITE);
        set.setCircleRadius(3f);
        set.setFillAlpha(65);
        set.setFillColor(color);
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setDrawCircleHole(false);
        set.setValueFormatter(valueFormatter);
        return set;
    }
    public void setCirclePoint(LineDataSet set, int low, int hight) {
        List<Integer> mColorList = new ArrayList<>();
        for (Entry entry : set.getValues()){
            if (entry.getY() < low || entry.getY() > hight){
                mColorList.add(Color.RED);
            }else {
                mColorList.add(Color.WHITE);
            }
        }
        set.setCircleColors(mColorList);
    }
    public void clear(){
        mLineChart.clear();
    }

    public void setData(ArrayList<ILineDataSet> lineDataSetsList){
        // 创建一个数据集的数据对象
        LineData data = new LineData(lineDataSetsList);
        data.setDrawValues(true);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);
        //设置数据
        mLineChart.setData(data);
        mLineChart.invalidate();
    }

    public void setDate(String date) {
        this.date = date;
        mLineChart.setNoDataText("没有采集到数据"+"("+date+")");
    }

    public void setxAxisMaximum(float xAxisMaximum) {
        this.xAxisMaximum = xAxisMaximum;
    }

    public void setxAxisMinimum(float xAxisMinimum) {
        this.xAxisMinimum = xAxisMinimum;
    }

    public void setxAxisTextSize(float xAxisTextSize) {
        this.xAxisTextSize = xAxisTextSize;
    }

    public void setxAxisLabelCount(int xAxisLabelCount) {
        this.xAxisLabelCount = xAxisLabelCount;
    }

    public void setxAxisTextColor(int xAxisTextColor) {
        this.xAxisTextColor = xAxisTextColor;
    }

    public void setyLeftAxisMaximum(float yLeftAxisMaximum) {
        this.yLeftAxisMaximum = yLeftAxisMaximum;
    }

    public void setyLeftAxisMinimum(float yLeftAxisMinimum) {
        this.yLeftAxisMinimum = yLeftAxisMinimum;
    }

    public void setyLeftAxisTextSize(float yLeftAxisTextSize) {
        this.yLeftAxisTextSize = yLeftAxisTextSize;
    }

    public void setyLeftAxisLabelCount(int yLeftAxisLabelCount) {
        this.yLeftAxisLabelCount = yLeftAxisLabelCount;
    }

    public void setyLeftAxisTextColor(int yLeftAxisTextColor) {
        this.yLeftAxisTextColor = yLeftAxisTextColor;
    }

    public void setyRightAxisMaximum(float yRightAxisMaximum) {
        this.yRightAxisMaximum = yRightAxisMaximum;
    }

    public void setyRightAxisMinimum(float yRightAxisMinimum) {
        this.yRightAxisMinimum = yRightAxisMinimum;
    }

    public void setyRightAxisTextSize(float yRightAxisTextSize) {
        this.yRightAxisTextSize = yRightAxisTextSize;
    }

    public void setyRightAxisLabelCount(int yRightAxisLabelCount) {
        this.yRightAxisLabelCount = yRightAxisLabelCount;
    }

    public void setyRightAxisTextColor(int yRightAxisTextColor) {
        this.yRightAxisTextColor = yRightAxisTextColor;
    }
}
