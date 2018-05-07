package soul.listener.com.humiture.a_presenters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import soul.listener.com.humiture.a_model.WheatherModel;
import soul.listener.com.humiture.activity.WheatherActivity;


/**
 * Created by liuyue on 2017/12/3.
 */
public class WheatherPresenter extends BasePresenter<WheatherActivity> {

    private String location;
    private WheatherModel wheatherModel;
    private String URL="http://wthrcdn.etouch.cn/WeatherApi?city=张家口";


    public WheatherPresenter(WheatherActivity wheatherActivity) {
        super(wheatherActivity);
        wheatherModel = new WheatherModel();
    }
    /*获得天气数据*/
    public void getWheather() {
        mView.showDialog();
        dealXML();
    }
    /*处理XML数据*/
    private void dealXML() {
        Flowable.create(new FlowableOnSubscribe<Document>() {
            @Override
            public void subscribe(FlowableEmitter<Document> e) throws Exception {
                Document doc = Jsoup.connect(URL).timeout(5000).get();
                if (doc == null){
                    e.onError(new Exception("获取天气失败"));
                } else{
                    e.onNext(doc);
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.io())
                .map(new Function<Document, WheatherModel>() {
                    @Override
                    public WheatherModel apply(Document doc) throws Exception {
                        String city=doc.body().select("city").text();
                        wheatherModel.setCity(city);
                        String updatetime=doc.body().select("updatetime").text();
                        wheatherModel.setUpdatetime(updatetime);
                        String wendu=doc.body().select("wendu").text();
                        wheatherModel.setWendu(wendu+"°C");
                        String fengli=doc.body().select("fengli").get(0).text();
                        wheatherModel.setFengli(fengli);
                        String shidu=doc.body().select("shidu").text();
                        wheatherModel.setShidu(shidu);
                        String fengxiang=doc.body().select("fengxiang").text();
                        wheatherModel.setFengxiang(fengxiang);
                        String sunrise=doc.body().select("sunrise_1").text();
                        wheatherModel.setSunrise(sunrise);
                        String sunset=doc.body().select("sunset_1").text();
                        wheatherModel.setSunset(sunset);
                        String aqi=doc.body().select("aqi").text();
                        wheatherModel.setAqi(aqi);
                        String quality=doc.body().select("quality").text();
                        wheatherModel.setQuality(quality);
                        int order=0;
                        for (int i=0;i<5;i++){
                            String date=doc.body().select("date").get(i).text();
                            wheatherModel.appendDate_List(date);
                            String high=doc.body().select("high").get(i).text();
                            wheatherModel.appendHigh_List(high);
                            String low=doc.body().select("low").get(i).text();
                            wheatherModel.appendLow_List(low);
                            wheatherModel.appendDay_type_List(doc.body().select("type").get(order).text());
                            wheatherModel.appendDay_fengxiang_List(doc.body().select("fengxiang").get(order+1).text());
                            wheatherModel.appendDay_fengli_List(doc.body().select("fengli").get(order+1).text());
                            order++;
                            wheatherModel.appendNight_type_List(doc.body().select("type").get(order).text());
                            wheatherModel.appendNight_fengxiang_List(doc.body().select("fengxiang").get(order+1).text());
                            wheatherModel.appendNight_fengli_List(doc.body().select("fengli").get(order+1).text());
                            order++;
                        }
                        return wheatherModel;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WheatherModel>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }
                    @Override
                    public void onNext(WheatherModel wheatherModel) {
                        if (mView != null){
                            mView.setDayDate(wheatherModel.getDate_List());
                            mView.setHigh(wheatherModel.getHigh_List());
                            mView.setLow(wheatherModel.getLow_List());
                            mView.setDayType(wheatherModel.getDay_type_List());
                            mView.setDayfengxiang(wheatherModel.getDay_fengxiang_List());
                            mView.setDayfengli(wheatherModel.getDay_fengli_List());
                            mView.setNightType(wheatherModel.getNight_type_List());
                            mView.setNightfengxiang(wheatherModel.getNight_fengxiang_List());
                            mView.setNightfengli(wheatherModel.getNight_fengli_List());
                            mView.setNowQuality(wheatherModel.getQuality());
                            mView.setNowQualityNumber(wheatherModel.getAqi());
                            mView.setTodayFengLi(wheatherModel.getFengxiang());
                            mView.setTodayFengLi(wheatherModel.getFengli());
                            mView.setTodayHumidity(wheatherModel.getShidu());
                            mView.setWendu(wheatherModel.getWendu());
                            mView.setTodaySunrise(wheatherModel.getSunrise());
                            mView.setTodaySunset(wheatherModel.getSunset());
                            int hour = Integer.parseInt(wheatherModel.getUpdatetime().substring(0, 2));
                            if (hour > 19 || hour < 5){
                                mView.setNowType(wheatherModel.getNight_type_List().get(0));
                                mView.refreshBackground(true, wheatherModel.getNight_type_List().get(0));
                            }else {
                                mView.setNowType(wheatherModel.getDay_type_List().get(0));
                                mView.refreshBackground(false, wheatherModel.getDay_type_List().get(0));
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        mView.getWeatherFailure("获取天气失败，正在重新获取！");
                    }

                    @Override
                    public void onComplete() {
                        mView.getWeatherSuccess();
                    }
                });
    }
}
