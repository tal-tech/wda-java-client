package cn.speiyou.wda.session.req;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/11 3:47 下午
 */
public class FromToParam {

    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    // 时间，单位秒
    private int duration;

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
