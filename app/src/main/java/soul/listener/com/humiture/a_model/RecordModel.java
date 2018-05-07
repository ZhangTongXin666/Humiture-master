package soul.listener.com.humiture.a_model;

/**
 * Created by kys_31 on 2017/12/18.
 */

public class RecordModel {

    private String date;
    private String blockLocation;
    private String block;
    private String buildNum;
    private String unitNum;
    private String room;
    private String temperature;
    private String timePoint;

    public RecordModel(){}

    public RecordModel(String date, String blockLocation, String block, String buildNum, String unitNum, String room, String temperature, String timePoint) {
        this.date = date;
        this.blockLocation = blockLocation;
        this.block = block;
        this.buildNum = buildNum;
        this.unitNum = unitNum;
        this.room = room;
        this.temperature = temperature;
        this.timePoint = timePoint;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getBlockLocation() {
        return blockLocation;
    }

    public void setBlockLocation(String blockLocation) {
        this.blockLocation = blockLocation;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "RecordModel{" +
                "date='" + date + '\'' +
                ", blockLocation='" + blockLocation + '\'' +
                ", block='" + block + '\'' +
                ", buildNum='" + buildNum + '\'' +
                ", unitNum='" + unitNum + '\'' +
                ", room='" + room + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
