package soul.listener.com.humiture.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import soul.listener.com.humiture.R;

/**
 * Created by kys_31 on 2017/12/16.
 */

public class MyMarkView extends MarkerView {
    private TextView mTvValue;
    public MyMarkView(Context context, int layoutResource) {
        super(context, layoutResource);
        mTvValue = findViewById(R.id.tv_value);
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight){
        super.refreshContent(entry, highlight);
        mTvValue.setText(String.valueOf(entry.getY()));
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
