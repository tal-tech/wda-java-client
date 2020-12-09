package cn.speiyou.wda.session.res;

/**
 * 创建Session的响应数据
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 4:17 下午
 */
public class CreateSession {

    private String sessionId;

    private Capabilities capabilities;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }
}
