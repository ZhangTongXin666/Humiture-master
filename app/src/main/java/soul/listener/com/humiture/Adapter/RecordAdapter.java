package soul.listener.com.humiture.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.logging.Logger;

import soul.listener.com.humiture.R;
import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.activity.RecordActivity;
import soul.listener.com.humiture.db.SQLHelper;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHoler> {

    private List<RecordModel> list;
    private Context context;

    public RecordAdapter(Context context, List<RecordModel> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHoler holer = new MyViewHoler(LayoutInflater.from(context).inflate(R.layout.item_record_view, parent, false));
        return holer;
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, final int position) {
        holder.mTvTemperature.setText(list.get(position).getTemperature()+"℃");
        holder.mTvAddress.setText(list.get(position).getBuildNum()+"-"+list.get(position).getUnitNum()+"-"+list.get(position).getRoom());
        holder.mTvTimePoint.setText(list.get(position).getTimePoint());
        holder.mTvData.setText(list.get(position).getDate());
        holder.mTvLocation.setText(list.get(position).getBlockLocation()+" "+list.get(position).getBlock());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHoler extends RecyclerView.ViewHolder{
        public TextView mTvTemperature;
        public TextView mTvAddress;
        public ImageView mIvDelete;
        public TextView mTvTimePoint;
        public TextView mTvData;
        public TextView mTvLocation;
        public MyViewHoler(View itemView) {
            super(itemView);
            mTvTemperature = itemView.findViewById(R.id.tv_below);
            mTvAddress = itemView.findViewById(R.id.tv_belowAddress);
            mTvTimePoint = itemView.findViewById(R.id.id_timePoint);
            mTvLocation = itemView.findViewById(R.id.tv_location);
            mTvData = itemView.findViewById(R.id.tv_data);
        }
    }
}
