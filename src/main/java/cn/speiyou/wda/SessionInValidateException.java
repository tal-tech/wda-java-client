package cn.speiyou.wda;

/**
 * session会话异常错误
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 3:19 下午
 */
public class SessionInValidateException extends Exception {

    private Error error;

    public SessionInValidateException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
