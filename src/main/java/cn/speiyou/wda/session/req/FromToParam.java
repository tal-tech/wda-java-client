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
    // 在拖动之前按下的时间，单位秒
    private double duration;
    // 在拖动后，停下的时间
    private double holdDuration;
    // 拖动速度
    private int velocity;

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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getHoldDuration() {
        return holdDuration;
    }

    public void setHoldDuration(double holdDuration) {
        this.holdDuration = holdDuration;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
