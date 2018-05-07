package soul.listener.com.humiture.a_presenters;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soul.listener.com.humiture.a_model.BlocksModel;
import soul.listener.com.humiture.a_model.SqlFactory;
import soul.listener.com.humiture.a_model.SqlInfoCallBack;
import soul.listener.com.humiture.db.SQLCursor;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.SqlStateCode;
import soul.listener.com.humiture.util.ToastUtil;

/**
 * Created by kys_31 on 2017/12/19.
 */

public abstract class HandlerDataPresenter<T> extends BasePresenter<T>{

    List<BlocksModel> mBlocksModelList = new ArrayList<>();
    List<String> mBlocLocationList = new ArrayList<>();
    List<String> mBlockList = new ArrayList<>();
    int mIntBlocksCount;

    HandlerDataPresenter(T t) {
        super(t);
    }
    /*初始化小区数据*/
    public void initBlockData() {
        try {
            String[] parts = {Constants.BLOCK_ID, Constants.BLOCK_NAME, Constants.BLOCK_LOACTION};
            SQLCursor.getPartData(Constants.BLOCKS_TABLE_NO, parts, 0, 0, new SqlInfoCallBack() {
                @Override
                public void Success(ArrayList<SqlFactory> sqlFactories) {
                    mIntBlocksCount = sqlFactories.size();
                    mBlocksModelList.clear();
                    for (int i = 0; i < mIntBlocksCount; i++) {
                        mBlocksModelList.add((BlocksModel)(sqlFactories.get(i)));
                    }
                    initResidentData();
                    blocksLocationData();
                    initPointUser();
                    showBlockLocation();
                }
                @Override
                public void Faild(int num) {
                    ToastUtil.makeText(SqlStateCode.getSqlFaildInfo(num));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPointUser() {}
        protected void showBlockLocation() {}
    /*获得小区位置数据*/
    void blocksLocationData() {
        mBlocLocationList.clear();
        for (int i = 0; i < mIntBlocksCount; i++) {
            if (!mBlocLocationList.contains(mBlocksModelList.get(i).getBlocksLocation()))
                mBlocLocationList.add(mBlocksModelList.get(i).getBlocksLocation());
        }
    }
    /*获得小区数据*/
    void blocksData(String blockLocation, TextView tvView) {
        mBlockList.clear();
        for (int i = 0; i < mIntBlocksCount; i++) {
            if (mBlocksModelList.get(i).getBlocksLocation().equals(blockLocation)) {
                mBlockList.add(mBlocksModelList.get(i).getBlocksName());
                tvView.setText(mBlockList.get(0));
            }
        }
    }
    /*初始化居民数据*/
    protected void initResidentData(){}


}
