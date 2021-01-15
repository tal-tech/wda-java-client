package cn.speiyou.wda;

/**
 * @author ：cmlanche
 * @date ：Created in 2021/1/15 4:54 下午
 */
public interface ILog {

    void logInfo(String tag, String info);

    void logError(String tag, String error, Throwable throwable);
}
