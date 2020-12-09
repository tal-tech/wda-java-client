package cn.speiyou.wda;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 3:29 下午
 */
public class BaseResponse<T> {

    // 返回值
    private T value;

    // 当前session id
    private String sessionId;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
