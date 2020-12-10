package cn.speiyou.wda;

import cn.speiyou.wda.custom.CustomApi;
import cn.speiyou.wda.findelement.FindElementApi;
import cn.speiyou.wda.orientation.OrientationApi;
import cn.speiyou.wda.screenshot.ScreenshotApi;
import cn.speiyou.wda.session.SessionApi;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

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
    // http client
    private OkHttpClient client = new OkHttpClient();
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

    public WDAClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.sessionApi = new SessionApi(this);
        this.screenshotApi = new ScreenshotApi(this);
        this.findElementApi = new FindElementApi(this);
        this.orientationApi = new OrientationApi(this);
        this.customApi = new CustomApi(this);
    }

    public String getBaseUrl() {
        return String.format("http://%s:%d", this.host, this.port);
    }

    /**
     * 获取控件树
     */
    public String getPageSource() {
        Request request = new Request.Builder().url(getBaseUrl() + "/source").build();
        try (Response res = client.newCall(request).execute()) {
            BaseResponse<String> r = new BaseResponse<>();
            r = JSON.parseObject(res.body().string(), r.getClass());
            if (StringUtils.isNotEmpty(r.getValue())) {
                return r.getValue();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查wda是否健康
     */
    public boolean health() {
        Request request = new Request.Builder().url(getBaseUrl() + "/health").build();
        try (Response res = client.newCall(request).execute()) {
            String s = Objects.requireNonNull(res.body()).string();
            return StringUtils.equals("I-AM-ALIVE", s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 关闭WDA
     */
    public boolean shutdown() {
        Request request = new Request.Builder().url(getBaseUrl() + "/wda/shutdown").build();
        try (Response res = client.newCall(request).execute()) {
            String s = Objects.requireNonNull(res.body()).string();
            return StringUtils.equals("Shutting down", s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public OkHttpClient getClient() {
        return client;
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
}
