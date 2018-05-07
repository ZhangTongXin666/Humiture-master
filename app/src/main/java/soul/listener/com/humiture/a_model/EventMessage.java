package soul.listener.com.humiture.a_model;

/**
 * Created by 流月 on 2018/4/23.
 *
 * @description
 */

public class EventMessage {
    private String type;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
