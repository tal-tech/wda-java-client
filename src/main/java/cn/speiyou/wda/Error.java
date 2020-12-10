package cn.speiyou.wda;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 2:59 下午
 */
public class Error {

    // 错误信息
    private String error;
    // 错误描述
    private String message;
    // 错误追踪
    private String traceback;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTraceback() {
        return traceback;
    }

    public void setTraceback(String traceback) {
        this.traceback = traceback;
    }
}
