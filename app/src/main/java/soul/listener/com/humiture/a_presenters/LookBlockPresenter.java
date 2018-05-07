package soul.listener.com.humiture.a_presenters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import soul.listener.com.humiture.Adapter.RecordAdapter;
import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.BlocksModel;
import soul.listener.com.humiture.a_model.PartDataSelectionModel;
import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.a_model.ResidentModel;
import soul.listener.com.humiture.a_model.SqlFactory;
import soul.listener.com.humiture.a_model.SqlInfoCallBack;
import soul.listener.com.humiture.a_model.TemperatureModel;
import soul.listener.com.humiture.activity.LookBlockActivity;
import soul.listener.com.humiture.activity.RecordActivity;
import soul.listener.com.humiture.db.SQLCursor;
import soul.listener.com.humiture.db.SQLHelper;
import soul.listener.com.humiture.util.CalculateUtil;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.SizeUtil;
import soul.listener.com.humiture.util.SqlStateCode;
import soul.listener.com.humiture.util.ToastUtil;
import soul.listener.com.humiture.util.ValueFormatterUtil;
import soul.listener.com.humiture.util.ViewUtil;
import soul.listener.com.humiture.view.BrokenLineView;
import soul.listener.com.humiture.view.MyMarkView;

/**
 * Created by kys_31 on 2017/12/16.
 */

public class LookBlockPresenter extends HandlerDataPresenter<LookBlockActivity> {
    private static List<ResidentModel> mResidentModeList = new ArrayList<>();
    private static List<TemperatureModel> mTemperatureModelList = new ArrayList<>();
    private int mIntResidentCount;
    private int mIntTemperatureCount;
    private List<String> mResidentList = new ArrayList<>();
    private String mStrBlockID = null;
    private int mIntDataCount;
    private int mIntPageCount;
    private int index = 1;
    private PopupWindow mPopupWindow;
    private BrokenLineView brokenLineView;
    private List<RecordModel> mRecordModelList = new ArrayList<>();

    public LookBlockPresenter(LookBlockActivity lookBlockActivity) {
        super(lookBlockActivity);
    }

    @Override
    protected void showBlockLocation() {
        if (mView != null)
        mView.setBlockLocation(mBlocLocationList.get(0));
        blocksData(mView.getBlockLocation(), mView.tvBlock);
    }

    /*开始查询*/
    public void startQuery() {
        try {
            for (int i = 0; i < mIntBlocksCount; i++) {
                if (mBlocksModelList.get(i).getBlocksName().equals(mView.getBlock())
                        && mBlocksModelList.get(i).getBlocksLocation().equals(mView.getBlockLocation())) {
                    mStrBlockID = mBlocksModelList.get(i).getBlocksId();
                }
            }

            PartDataSelectionModel model = new PartDataSelectionModel();
            model.setSelections(new String[]{Constants.RESIDENT_BLOCAKSID});
            model.setHazyOrExact(new String[]{"="});
            model.setConditions(new String[]{mStrBlockID});
            model.setParts(new String[]{"*"});
            model.setTableNameNo(Constants.RESIDENT_TABLE_NO);
            SQLCursor.getPartDataBySelection(mView, model, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mIntResidentCount = sqlFactories.size();
                    mResidentModeList.clear();
                    for (int i = 0; i < mIntResidentCount; i++) {
                        mResidentModeList.add((ResidentModel)(sqlFactories.get(i)));
                    }
                    getTemperaturesData();
                }

                @Override
                public void Faild(int num) {
                   if (brokenLineView != null){
                       brokenLineView.clear();
                   }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*根据选择的小区获得温度数据*/
    private void getTemperaturesData() {
        try {
            String[] parts = {Constants.HUMITURE_RESIDENTID,Constants.HUMITURE_TEMPERATURE, Constants.HUMITURE_HUMIDUTY, Constants.HUMITURE_CURRENTDATE, Constants.HUMITURE_CURRENTTIME};
            String[] selections = new String[mIntResidentCount + 1];
            String[] hazyOrExact = new String[mIntResidentCount + 1];
            String[] conditions = new String[mIntResidentCount + 1];
            selections[0] = Constants.HUMITURE_CURRENTDATE;
            hazyOrExact[0] = "=";
            conditions[0] = mView.getDate();
            for (int i = 1; i < mIntResidentCount + 1; i++) {
                selections[i] = Constants.HUMITURE_RESIDENTID;
                hazyOrExact[i] = "=";
                conditions[i] = mResidentModeList.get(i-1).getResidenId();
            }

            SQLCursor.getPartDataBySelection(Constants.TEMPERATURE_TABLE_NO,parts, selections, hazyOrExact, conditions,0, 0, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mIntTemperatureCount = sqlFactories.size();
                    mTemperatureModelList.clear();
                    for (int i = 0; i < mIntTemperatureCount; i++) {
                        mTemperatureModelList.add((TemperatureModel) (sqlFactories.get(i)));
                    }
                    calculatorAllValue();
                }
                @Override
                public void Faild(int num) {
                    mView.lvLineChat .clear();
                    ToastUtil.makeText("没有采集到数据");
                }
            },true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*计算温度平均值、最大值、最小值*/
    private void calculatorAllValue() {
        CalculateUtil.getInstance().mAvgValueList.clear();
        CalculateUtil.getInstance().mMinValueList.clear();
        CalculateUtil.getInstance().mMaxValueList.clear();
        for (int i = 0; i < mIntTemperatureCount; i++) {
            CalculateUtil.getInstance().startCalculator(mTemperatureModelList.get(i).getCurrentTime().substring(0, 2),
                    mTemperatureModelList.get(i).getTemperature(), false);
            if (i == (mIntTemperatureCount - 1)){
                CalculateUtil.getInstance().startCalculator(mTemperatureModelList.get(i).getCurrentTime().substring(0, 2),
                        mTemperatureModelList.get(i).getTemperature(), true);
            }
        }
        saveToRecord();
        startDraw();
    }
    /*将温度低于标准值的数据保存到本地，并提醒用户是否查看*/
    private void saveToRecord() {
        mRecordModelList.clear();
        RecordModel recordModel = null;
        String residentID;
        for (int i = 0; i < mIntTemperatureCount; i++) {
            if (Double.valueOf(mTemperatureModelList.get(i).getTemperature()) < Constants.STAND_LOW_TEMPERATURE){
                recordModel  = new RecordModel();
                recordModel.setDate(mView.getDate());
                recordModel.setBlockLocation(mView.getBlockLocation());
                recordModel.setBlock(mView.getBlock());
                recordModel.setTemperature(mTemperatureModelList.get(i).getTemperature());
                recordModel.setTimePoint(mTemperatureModelList.get(i).getCurrentTime());
                residentID = mTemperatureModelList.get(i).getResidentId();
                for (int j = 0; j < mResidentModeList.size(); j++) {
                    if (residentID.equals(mResidentModeList.get(j).getResidenId())){
                        recordModel.setBuildNum(mResidentModeList.get(j).getBuildingNo());
                        recordModel.setUnitNum(mResidentModeList.get(j).getResidentUnit());
                        recordModel.setRoom(mResidentModeList.get(j).getResidentRoomNo());
                    }
                }
                mRecordModelList.add(0, recordModel);
            }
        }
        if (recordModel != null){
            Dialog remindDialog = new AlertDialog.Builder(mView)
                    .setMessage("是否立即查看温度低于18℃的用户信息")
                    .setPositiveButton("立即查看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                showUserTemperature();
                        }
                    })
                    .setNegativeButton("暂不查看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
            remindDialog.setCanceledOnTouchOutside(false);
            remindDialog.show();
        }
    }

    /*立即查看指定小区温度过低用户*/
    private void showUserTemperature() {
        Dialog dialog = new AlertDialog.Builder(mView).create();
        View view = LayoutInflater.from(mView).inflate(R.layout.dialog_usertemperature_view, null);
        dialog.show();
        dialog.getWindow().setContentView(view);
        RecyclerView rvView = view.findViewById(R.id.rv_view);
        RecordAdapter adapter = new RecordAdapter(mView, mRecordModelList);
        rvView.setLayoutManager(new LinearLayoutManager(mView));
        rvView.setAdapter(adapter);
    }

    /*显示popWindow过滤数据*/
    public void showPop(final int num, final TextView tvView) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            final List<String> list;
            if (num == Constants.SHOW_BLOCKLOCATION) {
                list = mBlocLocationList;
            } else {
                list = mBlockList;
            }
            View viewPop = LayoutInflater.from(mView).inflate(R.layout.pop_view, null);
            mPopupWindow = new PopupWindow(viewPop, SizeUtil.getViewWidth(mView.tvBlock), ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.showAsDropDown(tvView);
            mPopupWindow.update();
            ListView listView = viewPop.findViewById(R.id.lv_view);
            listView.setAdapter(new ArrayAdapter<>(mView, R.layout.item_select_view, list));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tvView.setText(list.get(i));
                    mPopupWindow.dismiss();
                }
            });
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    switch (num) {
                        case Constants.SHOW_BLOCKLOCATION:
                            blocksData(mView.getBlockLocation(), mView.tvBlock);
                            break;
                        case Constants.SHOW_BLOCK:
                            startQuery();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
    /*上一页*/
    public void upPage() {
        index--;
        mView.setNextClick(true);
        if (index == 1) {
            mView.setUpClick(false);
        }
        Constants.num = 1;
        fillData(12);
    }
    /*下一页*/
    public void nextPage() {
        index++;
        mView.setUpClick(true);
        if (index == mIntPageCount) {
            mView.setNextClick(false);
        }
        Constants.num = 2;
        fillData(mIntDataCount);
    }
    /*开始绘画*/
    private void startDraw(){
        brokenLineView  = new BrokenLineView(mView.lvLineChat, mView);
        Constants.num = 1;
        index = 1;
        mIntPageCount = 1;
        mView.lvLineChat.clear();
        mIntDataCount = CalculateUtil.getInstance().mAvgValueList.size();
        if (mIntDataCount < 5){
            return;
        }
        if ( mIntDataCount > 12){
            mIntPageCount = 2;
            mView.setNextClick(true);
        }

        brokenLineView.showXAxisLine(ValueFormatterUtil.getXAxisTimePoint());
        brokenLineView.setyLeftAxisMaximum(42f);
        brokenLineView.showLeftYAxisLine();
        brokenLineView.showRightYAxisLine();
        fillData(mIntDataCount > 12? 12: mIntDataCount);
        brokenLineView.showLegend();
    }
    /*填充数据*/
    private void fillData(int count) {
        ArrayList<Entry> maxValueList = new ArrayList<>();
        for (int i = (index - 1) * 12; i < count; i++) {
            maxValueList.add(new Entry(i - (index - 1) * 12, Float.valueOf(CalculateUtil.getInstance().mMaxValueList.get(i))));
        }
        ArrayList<Entry> minValueList = new ArrayList<>();
        for (int i = (index - 1) * 12; i < count; i++) {
            minValueList.add(new Entry(i - (index - 1) * 12, Float.valueOf(CalculateUtil.getInstance().mMinValueList.get(i))));
        }
        ArrayList<Entry> avgValueList = new ArrayList<>();
        for (int i = (index - 1) * 12; i < count; i++) {
            avgValueList.add(new Entry(i - (index - 1) * 12, Float.valueOf(CalculateUtil.getInstance().mAvgValueList.get(i))));
        }
        LineDataSet set1 = brokenLineView.getLineDataSet(maxValueList, R.string.temperatureMax,YAxis.AxisDependency.LEFT, Color.YELLOW, ValueFormatterUtil.getTemperatureFormat());
        LineDataSet set2 = brokenLineView.getLineDataSet(avgValueList, R.string.temperatureAvg,YAxis.AxisDependency.LEFT, Color.GREEN, ValueFormatterUtil.getTemperatureFormat());
        LineDataSet set3 = brokenLineView.getLineDataSet(minValueList, R.string.temperatureMin,YAxis.AxisDependency.LEFT, Color.BLUE, ValueFormatterUtil.getTemperatureFormat());
        brokenLineView.setCirclePoint(set1, Constants.STAND_LOW_TEMPERATURE, Constants.STAND_HIGHT_TEMPERATURE);
        brokenLineView.setCirclePoint(set2, Constants.STAND_LOW_TEMPERATURE, Constants.STAND_HIGHT_TEMPERATURE);
        brokenLineView.setCirclePoint(set3, Constants.STAND_LOW_TEMPERATURE, Constants.STAND_HIGHT_TEMPERATURE);
        ArrayList<ILineDataSet> lineDataSetsList = new ArrayList<>();
        lineDataSetsList.add(set1);
        lineDataSetsList.add(set3);
        lineDataSetsList.add(set2);
        brokenLineView.setData(lineDataSetsList);
    }


}
