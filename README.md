# 《室内温湿度采集》 
 主要使用：JDBC与远处数据库通信、RXJAVA异步通信、MPAndroidChart绘制折线图。
<br><br>
<h3>MPAndroidchart ------ 折线图常用API</h3>
<pre><code>
/**
     * 构造函数：用于初始化LineChart并配置参数
     * @param mLineChart LineChart
     * @param context 上下文环境
     */
    public BrokenLineView(LineChart mLineChart, Context context){
        if (mLineChart != null){
            this.mLineChart = mLineChart;
        }else {
            mLineChart = new LineChart(context);
        }
        mLineChart.setEnabled(true); // 启动折线图
        mLineChart.setTouchEnabled(true); // 折线图可触摸
        MyMarkView myMarkView = new MyMarkView(context, R.layout.view_pop_selevt_value); // 自定义标记
        mLineChart.setMarker(myMarkView);//设置标记位
        mLineChart.setDragEnabled(true);//折线图可拖拽
        mLineChart.setDragDecelerationFrictionCoef(0.9f);//设置拖拽时 减速摩擦力。值越大 停下来的越慢
        mLineChart.setScaleEnabled(true);//折线图可缩放
        mLineChart.setDrawGridBackground(false); // 是否画出网格型背景
        mLineChart.setHighlightPerDragEnabled(true);//设置拖拽时高亮
        mLineChart.setBackgroundColor(Color.BLACK); //设置背景颜色
        mLineChart.animateXY(xAnimateTime, yAnimateTime); //初始折线图时x,y轴同时出现动画效果。
    }

    /**
     * 初始化X轴被配置X轴相关参数,
     * @param iAxisValueFormatter  自定义的X轴值格式化
     * @NOTE Y轴配置参数基本同X轴，下面就不介绍了。
     */
    public void showXAxisLine(IAxisValueFormatter iAxisValueFormatter){
        XAxis mXAxis = mLineChart.getXAxis(); //获得折线图的X轴
        mXAxis.setDrawAxisLine(true); // 是否画出X轴线
        mXAxis.setDrawGridLines(false); // 是否画出X轴的网格线
        mXAxis.setTextColor(xAxisTextColor); //设置X轴值的文本颜色
        mXAxis.setLabelCount(xAxisLabelCount, true);// 设置X轴标签数量
        mXAxis.setTextSize(xAxisTextSize);//设置X轴值的文本大小
        mXAxis.setAxisMaximum(xAxisMaximum); //设置X轴值的最大值
        mXAxis.setAxisMinimum(xAxisMinimum); //设置X轴值得最小值
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        if (iAxisValueFormatter != null)
        mXAxis.setValueFormatter(iAxisValueFormatter); //设置自定义的X轴值格式化
    }


    /**
     * 获得图例并配置图例的参数
     */
    public void showLegend(){
        Legend l = mLineChart.getLegend(); // 获得图例
        l.setForm(Legend.LegendForm.LINE); //设置图例的显示样式为线型
        l.setTextSize(11f); // 设置文本大小
        l.setTextColor(Color.WHITE);//设置图例文本颜色
        /*设置图例显示的位置*/
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL); //设置图例排列方式
        l.setFormToTextSpace(12f);//设置图例之间的间隔
    }

    /**
     * 构造折线上的数据，它们设置在折线集合中
     * @param list 元素为Entry的集合，元素必须是Entry
     * @param strID 折线上每个点的文本内容 ------ 我这里就内容写成资源文件引用
     * @param dependency 根据左Y轴还是右Y轴来显示折线
     * @param color  折线的颜色
     * @param valueFormatter 折线上自定义的格式化值
     * @return 构造成功的折线
     */
    public LineDataSet getLineDataSet(List<Entry> list, int strID, YAxis.AxisDependency dependency, int color, IValueFormatter valueFormatter) {
        LineDataSet set;
        if (list == null || list.size() == 0) return null;
        set = new LineDataSet(list, MyApplication.getContext().getResources().getString(strID));
        if (dependency != null)
        set.setAxisDependency(dependency);// 设置依据的轴
        if (color >= 0)
        set.setColor(color);//设置折线颜色
        set.setLineWidth(2f); //设置折线宽度
        set.setCircleColor(Color.WHITE); // 设置折线上圆点的颜色
        set.setCircleRadius(3f);//设置折线上圆点的半径
        set.setFillAlpha(65);//设置填充透明度
        set.setFillColor(color);//设置填充的颜色
        set.setHighLightColor(Color.rgb(244, 117, 117));//设置高亮颜色
       if (valueFormatter != null)
        set.setValueFormatter(valueFormatter); //设置自定义的格式化值
        return set;
    }


    /**
     * 将构造好的折线配置到折线图中
     * @param lineDataSetsList 构造好的折线集合
     */
    public void setData(ArrayList<ILineDataSet> lineDataSetsList){
        // 创建一个数据集的数据对象
        LineData data = new LineData(lineDataSetsList); //将折线集合转换为折线数据
        data.setDrawValues(true); // 允许画值
         data.setValueTextColor(Color.WHITE); // 画出值的颜色
        data.setValueTextSize(9f); //值文本的大小
        //设置数据
        mLineChart.setData(data);//给折线图配置折现数据
        mLineChart.invalidate(); // 开始绘制折线图
    }
</code></pre>
