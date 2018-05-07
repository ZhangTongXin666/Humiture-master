package soul.listener.com.humiture.a_model;

/**
 * @author kuan
 *         Created on 2017/11/14.
 * @description
 */

public class BlocksModel extends SqlFactory{
    private String blocksId;
    private String blocksName;
    private String blocksLocation;
    private String blocksContact;
    private String blocksContaceTel;

    public String getBlocksId() {
        return blocksId;
    }

    public void setBlocksId(String blocksId) {
        this.blocksId = blocksId;
    }

    public String getBlocksName() {
        return blocksName;
    }

    public void setBlocksName(String blocksName) {
        this.blocksName = blocksName;
    }

    public String getBlocksLocation() {
        return blocksLocation;
    }

    public void setBlocksLocation(String blocksLocation) {
        this.blocksLocation = blocksLocation;
    }

    public String getBlocksContact() {
        return blocksContact;
    }

    public void setBlocksContact(String blocksContact) {
        this.blocksContact = blocksContact;
    }

    public String getBlocksContaceTel() {
        return blocksContaceTel;
    }

    public void setBlocksContaceTel(String blocksContaceTel) {
        this.blocksContaceTel = blocksContaceTel;
    }

    @Override
    public String toString() {
        return "BlocksModel{" +
                "blocksId='" + blocksId + '\'' +
                ", blocksName='" + blocksName + '\'' +
                ", blocksLocation='" + blocksLocation + '\'' +
                ", blocksContact='" + blocksContact + '\'' +
                ", blocksContaceTel='" + blocksContaceTel + '\'' +
                '}';
    }
}
