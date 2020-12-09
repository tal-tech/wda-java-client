package cn.speiyou.wda.session.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 5:24 下午
 */
public class Status {

    // 状态，成功是success
    private String state;
    // 提示信息
    private String message;
    // 是否准备好
    private boolean ready;
    // 手机操作系统信息
    private OSInfo os;
    // 构建wda的信息
    private BuildInfo build;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public OSInfo getOs() {
        return os;
    }

    public void setOs(OSInfo os) {
        this.os = os;
    }

    public BuildInfo getBuild() {
        return build;
    }

    public void setBuild(BuildInfo build) {
        this.build = build;
    }
}
