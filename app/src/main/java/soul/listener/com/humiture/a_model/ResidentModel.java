package soul.listener.com.humiture.a_model;

import java.io.Serializable;

/**
 * @author kuan
 *         Created on 2017/11/14.
 * @description
 */

public class ResidentModel extends SqlFactory implements Serializable{
    private String residenId;
    private String residentName;
    private String blocksId;
    private String buildingNo;
    private String residentUnit;
    private String residentRoomNo;
    private String residentTel1;
    private String residentTel2;
    private String residentTel3;
    private String residentIEEEAddress;

    public String getResidenId() {
        return residenId;
    }

    public void setResidenId(String residenId) {
        this.residenId = residenId;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getBlocksId() {
        return blocksId;
    }

    public void setBlocksId(String blocksId) {
        this.blocksId = blocksId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getResidentUnit() {
        return residentUnit;
    }

    public void setResidentUnit(String residentUnit) {
        this.residentUnit = residentUnit;
    }

    public String getResidentRoomNo() {
        return residentRoomNo;
    }

    public void setResidentRoomNo(String residentRoomNo) {
        this.residentRoomNo = residentRoomNo;
    }

    public String getResidentTel1() {
        return residentTel1;
    }

    public void setResidentTel1(String residentTel1) {
        this.residentTel1 = residentTel1;
    }

    public String getResidentTel2() {
        return residentTel2;
    }

    public void setResidentTel2(String residentTel2) {
        this.residentTel2 = residentTel2;
    }

    public String getResidentTel3() {
        return residentTel3;
    }

    public void setResidentTel3(String residentTel3) {
        this.residentTel3 = residentTel3;
    }

    public String getResidentIEEEAddress() {
        return residentIEEEAddress;
    }

    public void setResidentIEEEAddress(String residentIEEEAddress) {
        this.residentIEEEAddress = residentIEEEAddress;
    }

    @Override
    public String toString() {
        return "ResidentModel{" +
                "residenId='" + residenId + '\'' +
                ", residentName='" + residentName + '\'' +
                ", blocksId='" + blocksId + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", residentUnit='" + residentUnit + '\'' +
                ", residentRoomNo='" + residentRoomNo + '\'' +
                ", residentTel1='" + residentTel1 + '\'' +
                ", residentTel2='" + residentTel2 + '\'' +
                ", residentTel3='" + residentTel3 + '\'' +
                ", residentIEEEAddress='" + residentIEEEAddress + '\'' +
                '}';
    }
}
