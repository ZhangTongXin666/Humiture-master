package soul.listener.com.humiture.a_presenters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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

import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.BlocksModel;
import soul.listener.com.humiture.a_model.PartDataSelectionModel;
import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.a_model.ResidentModel;
import soul.listener.com.humiture.a_model.SqlFactory;
import soul.listener.com.humiture.a_model.SqlInfoCallBack;
import soul.listener.com.humiture.a_model.TemperatureModel;
import soul.listener.com.humiture.activity.HumitureActivity;
import soul.listener.com.humiture.db.SQLCursor;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.SizeUtil;
import soul.listener.com.humiture.util.SqlStateCode;
import soul.listener.com.humiture.util.TimeUtil;
import soul.listener.com.humiture.util.ToastUtil;
import soul.listener.com.humiture.util.ValueFormatterUtil;
import soul.listener.com.humiture.util.ViewUtil;
import soul.listener.com.humiture.view.BrokenLineView;
import soul.listener.com.humiture.view.MyMarkView;

/**
 * Created by kys_31 on 2017/12/5.
 */

public class HumiturePresenter extends HandlerDataPresenter<HumitureActivity> {
    private PopupWindow mPopupWindow;
    private List<String> mBulidindNumberList = new ArrayList<>();
    private List<String> mUnitList = new ArrayList<>();
    private List<String> mHomeList = new ArrayList<>();
    private List<ResidentModel> mResidentModelList = new ArrayList<>();
    private List<TemperatureModel> mTemperatureModelList = new ArrayList<>();
    private int mIntResidentCount;
    private int mIntTemperatureCount;
    private String mStrBlockID;
    private int mIntDataCount = 12;
    private int mIntPageCount = 1;
    private int index = 1;
    private TextView mTvBlockLocation;
    private TextView mTvBlock;
    private TextView mTvBuildingIndex;
    private TextView mTvUnit;
    private TextView mTvHomeNumber;
    private TextView mTvDate;
    private BrokenLineView brokenLineView;
    private String residentID = "-1";

    public HumiturePresenter(HumitureActivity humitureActivity) {
        super(humitureActivity);
    }
    @Override
    protected void initResidentData() {
        try {
            SQLCursor.getData(mView, Constants.RESIDENT_TABLE_NO, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mIntResidentCount = sqlFactories.size();
                    mResidentModelList.clear();
                    for (int i = 0; i < mIntResidentCount; i++) {
                        mResidentModelList.add((ResidentModel) sqlFactories.get(i));
                    }
                    if (mView != null){
                        mView.userLogin();
                    }
                }
                @Override
                public void Faild(int num) {
                    ToastUtil.makeText(SqlStateCode.getSqlFaildInfo(num));
                    mView.hideDialog();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*获得楼号数据*/
    private void buildingNumberData() {
        mBulidindNumberList.clear();
        mStrBlockID = null;
        for (int i = 0; i < mIntBlocksCount; i++) {
            if (mBlocksModelList.get(i).getBlocksName().equals(mTvBlock.getText().toString())) {
                mStrBlockID = mBlocksModelList.get(i).getBlocksId();
            }
        }

        for (int i = 0; i < mIntResidentCount; i++) {
            if (mResidentModelList.get(i).getBlocksId().equals(mStrBlockID)) {
                mBulidindNumberList.add(mResidentModelList.get(i).getBuildingNo());
                mTvBuildingIndex.setText(mBulidindNumberList.get(0));
            }
        }
        unitData();
    }

    /*获得楼单元数据*/
    private void unitData() {
        mUnitList.clear();
        for (int i = 0; i < mIntResidentCount; i++) {
            if (mResidentModelList.get(i).getBuildingNo().equals(mTvBuildingIndex.getText().toString())
                    && mResidentModelList.get(i).getBlocksId().equals(mStrBlockID)) {
                mUnitList.add(mResidentModelList.get(i).getResidentUnit());
                mTvUnit.setText(mUnitList.get(0));
            }
        }
        roomData();
    }

    /*获得房间号数据*/
    private void roomData() {
        mHomeList.clear();
        for (int i = 0; i < mIntResidentCount; i++) {
            if (mResidentModelList.get(i).getResidentUnit().equals(mTvUnit.getText().toString())
                    && mResidentModelList.get(i).getBuildingNo().equals(mTvBuildingIndex.getText().toString())
                    && mResidentModelList.get(i).getBlocksId().equals(mStrBlockID)) {
                mHomeList.add(mResidentModelList.get(i).getResidentRoomNo());
                mTvHomeNumber.setText(mHomeList.get(0));
            }
        }
    }

    /*显示需要过滤的数据*/
    public void showFilterDialog(final boolean click) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mView);
        final Dialog filterDialog = builder.create();
        final View viewDialog = LayoutInflater.from(mView).inflate(R.layout.dialog_selectuselocation, null);
        filterDialog.show();
        filterDialog.getWindow().setContentView(viewDialog);
        filterDialog.setCanceledOnTouchOutside(false);

        LinearLayout llBlockLocation = viewDialog.findViewById(R.id.ll_blockLocation);
        LinearLayout llBlock = viewDialog.findViewById(R.id.ll_block);
        LinearLayout llBuildingIndex = viewDialog.findViewById(R.id.ll_buildingIndex);
        LinearLayout llUnit = viewDialog.findViewById(R.id.ll_unit);
        LinearLayout llHomeNumber = viewDialog.findViewById(R.id.ll_homeNumber);
        LinearLayout llDate = viewDialog.findViewById(R.id.ll_date);
        if (click){
            ViewUtil.setDialogWindowAttr(filterDialog, (int) (SizeUtil.getWindowWidth(mView) * 0.9), (int) (SizeUtil.getWindowHeight(mView) * 0.9));
            llBlockLocation.setVisibility(View.VISIBLE);
            llBlock.setVisibility(View.VISIBLE);
            llBuildingIndex.setVisibility(View.VISIBLE);
            llUnit.setVisibility(View.VISIBLE);
            llHomeNumber.setVisibility(View.VISIBLE);
        }else {
            ViewUtil.setDialogWindowAttr(filterDialog, (int) (SizeUtil.getWindowWidth(mView) * 0.9), (int) (SizeUtil.getWindowHeight(mView) * 0.35));
            llBlockLocation.setVisibility(View.GONE);
            llBlock.setVisibility(View.GONE);
            llBuildingIndex.setVisibility(View.GONE);
            llUnit.setVisibility(View.GONE);
            llHomeNumber.setVisibility(View.GONE);
        }

        mTvBlockLocation = viewDialog.findViewById(R.id.tv_blockLocation);
        mTvBlock = viewDialog.findViewById(R.id.tv_block);
        mTvBuildingIndex = viewDialog.findViewById(R.id.tv_buildingIndex);
        mTvUnit = viewDialog.findViewById(R.id.tv_unit);
        mTvHomeNumber = viewDialog.findViewById(R.id.tv_homeNumner);
        mTvDate = viewDialog.findViewById(R.id.tv_date);
        mTvDate.setText(TimeUtil.getSystemTime());

        if (mBlocLocationList.size() > 0){
            mTvBlockLocation.setText(mBlocLocationList.get(0));
            blocksData(mTvBlockLocation.getText().toString(), mTvBlock);
            buildingNumberData();
        }

        Button btQuery = viewDialog.findViewById(R.id.bt_query);
        Button btCancle = viewDialog.findViewById(R.id.bt_cancle);
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.setAddress(mTvBlockLocation.getText().toString()+" "+
                        mTvBlock.getText().toString()+" "+mTvBuildingIndex.getText().toString()+"-"+mTvUnit.getText().toString()+"-"
                        +mTvHomeNumber.getText().toString());
                mView.setDate(mTvDate.getText().toString());
                startQuery();
                filterDialog.dismiss();
            }
        });
        llBlockLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(Constants.SHOW_BLOCKLOCATION, mBlocLocationList, mTvBlockLocation);
            }
        });
        llBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(Constants.SHOW_BLOCK, mBlockList, mTvBlock);
            }
        });
        llBuildingIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(Constants.SHOW_BUILDINGINDEX, mBulidindNumberList, mTvBuildingIndex);
            }
        });
        llUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(Constants.SHOW_UNIT, mUnitList, mTvUnit);
            }
        });
        llHomeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(Constants.SHOW_HOMENUMBER, mHomeList, mTvHomeNumber);
            }
        });

        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeUtil.selectDate(mView, mTvDate);
            }
        });

    }

    /*显示PopWindow过滤数据*/
    private void showPop(final int num, final List<String> list, final TextView tvText){
        if (mPopupWindow != null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }else {
            View viewPop = LayoutInflater.from(mView).inflate(R.layout.pop_view, null);
            mPopupWindow = new PopupWindow(viewPop, SizeUtil.getViewWidth(mTvBlock),ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.showAsDropDown(tvText);
            mPopupWindow.update();
            ListView listView  = viewPop.findViewById(R.id.lv_view);
            listView.setAdapter(new ArrayAdapter<>(mView, R.layout.item_select_view, list));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tvText.setText(list.get(i));
                    mPopupWindow.dismiss();
                }
            });
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    switch (num){
                        case Constants.SHOW_BLOCKLOCATION:
                            blocksData(mTvBlockLocation.getText().toString(), mTvBlock);
                            buildingNumberData();
                            break;
                        case Constants.SHOW_BLOCK:
                            buildingNumberData();
                            break;
                        case Constants.SHOW_BUILDINGINDEX:
                            unitData();
                            break;
                        case Constants.SHOW_UNIT:
                            roomData();
                            break;
                        default:break;
                    }
                }
            });
        }
    }
    /*根据选择的用户查询温度数据*/
    private void startQuery(){
        mTemperatureModelList.clear();
        residentID = "-1";
        for (int i = 0; i < mIntResidentCount; i++) {
            if (mResidentModelList.get(i).getResidentRoomNo().equals(mTvHomeNumber.getText().toString())
                    && mResidentModelList.get(i).getResidentUnit().equals(mTvUnit.getText().toString())
                    && mResidentModelList.get(i).getBuildingNo().equals(mTvBuildingIndex.getText().toString())
                    && mResidentModelList.get(i).getBlocksId().equals(mStrBlockID)){
                residentID = mResidentModelList.get(i).getResidenId();
            }
        }
      filterDataByData(residentID, mView.getDate());
    }

    private void filterDataByData(String residentID, String data) {
        Log.e("TAG", "查询条件: "+residentID+"   "+data );
        mTemperatureModelList.clear();
        try {
            PartDataSelectionModel model = new PartDataSelectionModel();
            String[] parts = {Constants.HUMITURE_RESIDENTID,Constants.HUMITURE_TEMPERATURE, Constants.HUMITURE_HUMIDUTY, Constants.HUMITURE_CURRENTDATE, Constants.HUMITURE_CURRENTTIME};
            String[] selections = {Constants.HUMITURE_RESIDENTID, Constants.HUMITURE_CURRENTDATE};
            String[] hazyOrExact = {"=","="};
            String[] conditions = {residentID, data};
            model.setTableNameNo(Constants.TEMPERATURE_TABLE_NO);
            model.setParts(parts);
            model.setSelections(selections);
            model.setHazyOrExact(hazyOrExact);
            model.setConditions(conditions);
            SQLCursor.getPartDataBySelection(mView, model, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mIntTemperatureCount = sqlFactories.size();
                    Log.e("TAG", "数据量: "+mIntTemperatureCount );
                    for (int i = 0; i < mIntTemperatureCount; i++) {
                        mTemperatureModelList.add((TemperatureModel) (sqlFactories.get(i)));
                        Log.e("TAG", "DATE: "+((TemperatureModel) (sqlFactories.get(i))).getCurrentDate() );
                    }
                    startDraw();
                }
                @Override
                public void Faild(int num) {
                    startDraw();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
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
    /*开始绘图*/
    private void startDraw(){
        Constants.num = 1;
        brokenLineView = new BrokenLineView(mView.mDoubleLineChar, mView);
        mIntPageCount = 1;
        index = 1;
        mView.setUpClick(false);
        mView.mDoubleLineChar.clear();
        mIntDataCount = mTemperatureModelList.size();
        if (mIntDataCount > 12){
            mIntPageCount = 2;
            mView.setNextClick(true);
        }
        brokenLineView.setDate(mView.getDate());

        if (mIntDataCount < 5){
            mView.setNextClick(false);
            brokenLineView.clear();
            return;
        }
        brokenLineView.showXAxisLine(ValueFormatterUtil.getXAxisValueFormat(mTemperatureModelList));
        brokenLineView.showLeftYAxisLine();
        brokenLineView.showRightYAxisLine();
        fillData(mIntDataCount > 12? 12 : mIntDataCount);
        brokenLineView.showLegend();
    }
    /*填充数据*/
    private void fillData(int count) {
        /*初始化数据*/
        ArrayList<Entry> humidityList = new ArrayList<>();
        for (int i = 0 + (index - 1) * 12; i < count; i++) {
            humidityList.add(new Entry(i - (index - 1) * 12, Float.valueOf(mTemperatureModelList.get(i).getHumidity())));
        }
        ArrayList<Entry> temperatureList = new ArrayList<>();
        for (int i = 0 + (index - 1) * 12; i < count; i++) {
            temperatureList.add(new Entry(i - (index - 1) * 12, Float.valueOf(mTemperatureModelList.get(i).getTemperature())));
        }
        /*初始化LineDataSet*/
        LineDataSet set1 = brokenLineView.getLineDataSet(humidityList, R.string.humidity, YAxis.AxisDependency.LEFT, Color.BLUE, new PercentFormatter());
        LineDataSet set2 = brokenLineView.getLineDataSet(temperatureList, R.string.temperature, YAxis.AxisDependency.RIGHT, Color.YELLOW, ValueFormatterUtil.getTemperatureFormat());
        brokenLineView.setCirclePoint(set1, Constants.STAND_LOW_HUMIDITY, Constants.STAND_HIGHT_HUMIDITY);
        brokenLineView.setCirclePoint(set2, Constants.STAND_LOW_TEMPERATURE, Constants.STAND_HIGHT_TEMPERATURE);
        ArrayList<ILineDataSet> lineDataSetsList = new ArrayList<>();
        lineDataSetsList.add(set1);
        lineDataSetsList.add(set2);
        brokenLineView.setData(lineDataSetsList);
    }

    /**
     * 通过手机号登录，执行时这一个用户的信息
     * @param residentModel 用户信息
     */
    public void showOnlyUser(ResidentModel residentModel){
        showFilterDialog(false);
        mStrBlockID = residentModel.getBlocksId();
        for (int i = 0; i < mIntBlocksCount; i++) {
            if (mStrBlockID.equals(mBlocksModelList.get(i).getBlocksId())){
                mTvBlockLocation.setText(mBlocksModelList.get(i).getBlocksLocation());
                mTvBlock.setText(mBlocksModelList.get(i).getBlocksName());
            }
        }
        mTvBuildingIndex.setText(residentModel.getBuildingNo());
        mTvUnit.setText(residentModel.getResidentUnit());
        mTvHomeNumber.setText(residentModel.getResidentRoomNo());
    }


    /*上一天*/
    public void lookUpDay(){
        mView.setDate(TimeUtil.changeDay(-1, mView.getDate()));
        filterDataByData(residentID, mView.getDate());
    }
    /*下一天*/
    public void lookNextDay(){
        mView.setDate(TimeUtil.changeDay(1, mView.getDate()));
        filterDataByData(residentID, mView.getDate());
    }
}
