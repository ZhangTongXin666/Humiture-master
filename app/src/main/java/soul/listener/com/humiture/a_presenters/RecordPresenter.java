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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.BlocksModel;
import soul.listener.com.humiture.a_model.PartDataSelectionModel;
import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.a_model.ResidentModel;
import soul.listener.com.humiture.a_model.SqlFactory;
import soul.listener.com.humiture.a_model.SqlInfoCallBack;
import soul.listener.com.humiture.a_model.TemperatureModel;
import soul.listener.com.humiture.activity.RecordActivity;
import soul.listener.com.humiture.db.SQLCursor;
import soul.listener.com.humiture.db.SQLHelper;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.SizeUtil;
import soul.listener.com.humiture.util.SqlStateCode;
import soul.listener.com.humiture.util.TimeUtil;
import soul.listener.com.humiture.util.ToastUtil;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class RecordPresenter extends HandlerDataPresenter<RecordActivity> {
    private TextView mTvBlockLocation;
    private TextView mTvBlock;
    private TextView mTvDate;
    private PopupWindow mPopupWindow;
    private List<TemperatureModel> mTemperatureModelList = new ArrayList<>();
    private List<String> mResdentIDList = new ArrayList<>();
    private List<ResidentModel> mResdentModelList = new ArrayList<>();
    private int mTemperatureCount;
    private int mResdentCount;
    private List<RecordModel> mAllData;

    public RecordPresenter(RecordActivity recordActivity) {
        super(recordActivity);
    }

    /*获得室内温度低于18℃的用户*/
    public void getTemperatureData(){
        PartDataSelectionModel model = new PartDataSelectionModel();
        String[] parts = {Constants.HUMITURE_RESIDENTID,Constants.HUMITURE_TEMPERATURE, Constants.HUMITURE_HUMIDUTY, Constants.HUMITURE_CURRENTDATE, Constants.HUMITURE_CURRENTTIME};
        String[] selections = {Constants.HUMITURE_TEMPERATURE};
        String[] hazyOrExact = {"<"};
        String[] conditions = {"18"};
        model.setTableNameNo(Constants.TEMPERATURE_TABLE_NO);
        model.setParts(parts);
        model.setSelections(selections);
        model.setHazyOrExact(hazyOrExact);
        model.setConditions(conditions);
        try {
            SQLCursor.getPartDataBySelection(mView, model, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mTemperatureCount = sqlFactories.size();
                    for (int i = 0; i < sqlFactories.size(); i++) {
                        mTemperatureModelList.add((TemperatureModel)sqlFactories.get(i));
                    }
                    initBlockData();
                }
                @Override
                public void Faild(int num) {

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*获得指定用户*/
    @Override
   protected void initPointUser(){

        try {
            SQLCursor.getData(mView, Constants.RESIDENT_TABLE_NO, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    Log.w("TAG", "全部数据："+sqlFactories.size());
                    for (int i = 0; i < sqlFactories.size(); i++) {
                        Log.w("TAG", "全部居民ID："+((ResidentModel)(sqlFactories.get(i))).getResidenId());
                    }
                }

                @Override
                public void Faild(int num) {

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mTemperatureCount; i++) {
           if (!mResdentIDList.contains(mTemperatureModelList.get(i).getResidentId())){
               mResdentIDList.add(mTemperatureModelList.get(i).getResidentId());
               Log.w("TAG", "低于18℃的居民ID："+mTemperatureModelList.get(i).getResidentId());
           }
       }
       mResdentCount = mResdentIDList.size();
       String[] selections = new String[mResdentCount];
       String[] hazyOrExacts = new String[mResdentCount];
       String[] conditions = new String[mResdentCount];
       String[] parts = {"*"};

       for (int i = 0; i < mResdentCount; i++) {
           selections[i] = Constants.RESIDENT_ID;
           hazyOrExacts[i] = "=";
           conditions[i] = mResdentIDList.get(i);
       }

       try {
           SQLCursor.getPartDataBySelection(Constants.RESIDENT_TABLE_NO, parts, selections, hazyOrExacts, conditions, 0, 0, new SqlInfoCallBack() {
               @Override
               public void Success(ArrayList<SqlFactory> sqlFactories) {
                   for (int i = 0; i < sqlFactories.size(); i++) {
                       mResdentModelList.add((ResidentModel)(sqlFactories.get(i)));
                       Log.w("TAG", "部分居民ID："+mResdentModelList.get(i).getResidenId());
                   }
                   mateData();
               }

               @Override
               public void Faild(int num) {

               }
           }, false);
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

   /*匹配数据*/
    private void mateData() {
        RecordModel model = null;
        mAllData = new ArrayList<>();
        for (int i = 0; i < mTemperatureCount; i++) {
            for (int j = 0; j < mResdentCount; j++) {
                if (mTemperatureModelList.get(i).getResidentId().equals(mResdentModelList.get(j).getResidenId())){
                    for (int k = 0; k < mIntBlocksCount; k++) {
                        if (mResdentModelList.get(j).getBlocksId().equals(mBlocksModelList.get(k).getBlocksId())){
                            model = new RecordModel();
                            model.setTimePoint(mTemperatureModelList.get(i).getCurrentTime());
                            model.setDate(mTemperatureModelList.get(i).getCurrentDate());
                            model.setTemperature(mTemperatureModelList.get(i).getTemperature());

                            model.setBuildNum(mResdentModelList.get(j).getBuildingNo());
                            model.setRoom(mResdentModelList.get(j).getResidentRoomNo());
                            model.setUnitNum(mResdentModelList.get(j).getResidentUnit());

                            model.setBlockLocation(mBlocksModelList.get(k).getBlocksLocation());
                            model.setBlock(mBlocksModelList.get(k).getBlocksName());
                            mAllData.add(0, model);
                        }
                    }
                }
            }
        }
        mView.showView(mAllData);
    }

    /*显示需要过滤的数据*/
    public void showFilterData(){
        final Dialog filterDialog = new AlertDialog.Builder(mView).create();
        View filterView = LayoutInflater.from(mView).inflate(R.layout.dialog_selectuselocation, null);
        filterDialog.show();
        filterDialog.getWindow().setContentView(filterView);
        LinearLayout llDate = filterView.findViewById(R.id.ll_date);
        LinearLayout llBlockLocation = filterView.findViewById(R.id.ll_blockLocation);
        LinearLayout llBlock = filterView.findViewById(R.id.ll_block);
        LinearLayout llBuildingIndex = filterView.findViewById(R.id.ll_buildingIndex);
        LinearLayout llUnit = filterView.findViewById(R.id.ll_unit);
        LinearLayout llHomeNumber = filterView.findViewById(R.id.ll_homeNumber);
        llBuildingIndex.setVisibility(View.GONE);
        llUnit.setVisibility(View.GONE);
        llHomeNumber.setVisibility(View.GONE);

        mTvBlockLocation = filterView.findViewById(R.id.tv_blockLocation);
        mTvBlock = filterView.findViewById(R.id.tv_block);
        mTvDate = filterView.findViewById(R.id.tv_date);
        mTvDate.setText(TimeUtil.getSystemTime());
        if(mBlocLocationList.size() > 0){
            mTvBlockLocation.setText(mBlocLocationList.get(0));
            blocksData(mTvBlockLocation.getText().toString(), mTvBlock);
        }

        Button btQuery = filterView.findViewById(R.id.bt_query);
        Button btCancle = filterView.findViewById(R.id.bt_cancle);
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<RecordModel> filterModel = new ArrayList<>();
                for (int i = 0; i < mAllData.size(); i++) {
                    if (mAllData.get(i).getDate().equals(mTvDate.getText().toString())
                            && mAllData.get(i).getBlock().equals(mTvBlock.getText().toString())
                            && mAllData.get(i).getBlockLocation().equals(mTvBlockLocation.getText().toString())){
                        filterModel.add(mAllData.get(i));
                    }
                }
                mView.showView(filterModel);
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
        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeUtil.selectDate(mView, mTvDate);
            }
        });

    }

    /*过滤数据*/
    private void showPop(final int num, final List<String> list, final TextView tvText){
        if (mPopupWindow != null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }else {
            View viewPop = LayoutInflater.from(mView).inflate(R.layout.pop_view, null);
            mPopupWindow = new PopupWindow(viewPop, SizeUtil.getViewWidth(mTvBlock), ViewGroup.LayoutParams.WRAP_CONTENT);
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
                            break;
                        default:break;
                    }
                }
            });
        }
    }

}
