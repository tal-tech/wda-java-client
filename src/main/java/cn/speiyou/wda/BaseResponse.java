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
    // 是否调用成功
    private boolean success;
    // 调用失败的错误信息
    private Error err;

    public BaseResponse() {
        this.success = false;
    }

    public BaseResponse(boolean success) {
        this.success = success;
    }

    public BaseResponse(T value) {
        this.success = true;
        this.value = value;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getErr() {
        return err;
    }

    public void setErr(Error err) {
        this.err = err;
    }
}
