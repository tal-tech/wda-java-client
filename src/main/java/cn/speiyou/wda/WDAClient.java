package cn.speiyou.wda;

import cn.speiyou.wda.alert.AlertApi;
import cn.speiyou.wda.custom.CustomApi;
import cn.speiyou.wda.element.ElementApi;
import cn.speiyou.wda.findelement.FindElementApi;
import cn.speiyou.wda.orientation.OrientationApi;
import cn.speiyou.wda.screenshot.ScreenshotApi;
import cn.speiyou.wda.session.SessionApi;

/**
 * WebDriverAgent Client
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 3:22 下午
 */
public class WDAClient {
    // 服务地址
    private String host;
    // 服务端口
    private int port;
    // Session模块Api
    private SessionApi sessionApi;
    // 截图模块Api
    private ScreenshotApi screenshotApi;
    // 查找元素模块Api
    private FindElementApi findElementApi;
    // 屏幕方向Api
    private OrientationApi orientationApi;
    // 自定义Api
    private CustomApi customApi;
    // 控件操作Api
    private ElementApi elementApi;
    // 对话框操作API
    private AlertApi alertApi;

    // http请求代理
    private HttpProxy httpProxy;

    // 当前请求的SessionId
    private String currentSessionId;

    public WDAClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.sessionApi = new SessionApi(this);
        this.screenshotApi = new ScreenshotApi(this);
        this.findElementApi = new FindElementApi(this);
        this.orientationApi = new OrientationApi(this);
        this.customApi = new CustomApi(this);
        this.elementApi = new ElementApi(this);
        this.alertApi = new AlertApi(this);
        this.httpProxy = new HttpProxy(this);
    }

    /**
     *
     * @param host
     * @param port
     * @param restDuration 相邻两次请求不要间隔太短
     */
    public WDAClient(String host, int port, int restDuration) {
        this(host, port);
        this.httpProxy.setRequestDuration(restDuration);
    }

    public String getBaseUrl() {
        return String.format("http://%s:%d", this.host, this.port);
    }

    /**
     * 获取控件树
     */
    public BaseResponse getPageSource() {
        return httpProxy.get(getBaseUrl() + "/source", null);
    }

    /**
     * 检查wda是否健康
     */
    public BaseResponse<Boolean> health() {
        return httpProxy.get(getBaseUrl() + "/health", null);
    }

    /**
     * 关闭WDA
     */
    public BaseResponse<Boolean> shutdown() {
        return httpProxy.get(getBaseUrl() + "/wda/shutdown", null);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public SessionApi getSessionApi() {
        return sessionApi;
    }

    public ScreenshotApi getScreenshotApi() {
        return screenshotApi;
    }

    public FindElementApi getFindElementApi() {
        return findElementApi;
    }

    public OrientationApi getOrientationApi() {
        return orientationApi;
    }

    public CustomApi getCustomApi() {
        return customApi;
    }

    public ElementApi getElementApi() {
        return elementApi;
    }

    public AlertApi getAlertApi() {
        return alertApi;
    }

    public HttpProxy getHttpProxy() {
        return httpProxy;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }
}
