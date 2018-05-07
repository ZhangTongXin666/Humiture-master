package soul.listener.com.humiture.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import soul.listener.com.humiture.a_model.RecordModel;
import soul.listener.com.humiture.util.Constants;
import soul.listener.com.humiture.util.MyApplication;
import soul.listener.com.humiture.util.ToastUtil;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class SQLHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    private static class InitSQLHlper{
        static final SQLHelper SQL_HELPER = new SQLHelper();
    }

    public static SQLHelper getInstance(){
        return InitSQLHlper.SQL_HELPER;
    }

    private SQLHelper(){
        this(MyApplication.getContext(), Constants.DATEBASE_NAME, null, Constants.DATEBASE_VERSION);
    }

    private SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database = sqLiteDatabase;
        String createTable = "create table "+Constants.TABLE_NAME+"(id INTEGER PRIMARY KEY, date text, blockLocation text, block text," +
                " buildNum text, unitNum text, room text, temperature text, timePoint text)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(RecordModel recordModel){
        if (!deleteSaved(recordModel)){
            database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("date", recordModel.getDate());
            values.put("blockLocation", recordModel.getBlockLocation());
            values.put("block", recordModel.getBlock());
            values.put("buildNum", recordModel.getBuildNum());
            values.put("unitNum", recordModel.getUnitNum());
            values.put("room", recordModel.getRoom());
            values.put("temperature", recordModel.getTemperature());
            values.put("timePoint", recordModel.getTimePoint());
            long l = database.insert(Constants.TABLE_NAME, null ,values);
            database.close();
        }
    }

    public void deleteData(RecordModel recordModel){
        database = getWritableDatabase();
        int i = database.delete(Constants.TABLE_NAME, "date = ? and blockLocation = ? and block = ? and buildNum = ? and unitNum = ? and " +
                "room = ?", new String[]{recordModel.getDate(), recordModel.getBlockLocation(), recordModel.getBlock(), recordModel.getBuildNum(),  recordModel.getUnitNum(), recordModel.getRoom()});
        database.close();

    }

    public List<RecordModel> queryDate(String date, String blockLocation, String block){
        database = getReadableDatabase();
        List<RecordModel> list = new ArrayList<>();
        Cursor cursor = database.query(Constants.TABLE_NAME, null, "date = ? and blockLocation = ? and block = ?", new String[]{date, blockLocation, block}, null, null, null);
        while (cursor.moveToNext()){
            list.add(0,new RecordModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)
                    ,cursor.getString(6), cursor.getString(7), cursor.getString(8)));
        }
        cursor.close();
        database.close();
        return list;
    }

    public List<RecordModel> quertAllDate(){
        database = getReadableDatabase();
        List<RecordModel> list = new ArrayList<>();
        Cursor cursor = database.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            list.add(0,new RecordModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)
                    ,cursor.getString(6), cursor.getString(7), cursor.getString(8)));
        }
        cursor.close();
        database.close();
        return list;
    }

    public RecordModel getLastData(){
        database = getReadableDatabase();
        RecordModel recordModel = null;
        Cursor cursor = database.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToLast()){
            recordModel = new RecordModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)
                    ,cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }
        return recordModel;
    }

    private boolean deleteSaved(RecordModel recordModel){
        List<RecordModel> list = quertAllDate();
        for (RecordModel model : list) {
            if (model.toString().equals(recordModel.toString())){
                return true;
            }
        }
        return false;
    }

}
