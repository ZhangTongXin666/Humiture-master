package soul.listener.com.humiture.a_views;

/**
 * Created by kys_31 on 2017/12/16.
 */

public interface LookBlockView {
    void setBlockLocation(String content);
    void setBlock(String content);
    void setDate(String content);
    String getBlockLocation();
    String getBlock();
    String getDate();
    void setUpClick(boolean click);
    void setNextClick(boolean click);
}
