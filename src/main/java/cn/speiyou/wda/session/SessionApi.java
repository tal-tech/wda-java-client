package cn.speiyou.wda.session;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.session.req.AppParam;
import cn.speiyou.wda.session.res.CreateSession;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 3:50 下午
 */
public class SessionApi extends BaseApi {


    public SessionApi(WDAClient client) {
        super(client);
    }

    /**
     * 创建Session
     */
    public BaseResponse<CreateSession> createSession() {
        return post(getBaseUrl() + "/session", "{\"capabilities\": {}}");
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse launchApp(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return post(getBaseUrlWithSession(sessionId) + "/wda/apps/launch", param);
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse<Integer> getAppState(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return post(getBaseUrlWithSession(sessionId) + "/wda/apps/state", param);
    }

    /**
     * 激活应用
     * @param bundleId 应用包id
     */
    public BaseResponse activateApp(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return post(getBaseUrlWithSession(sessionId) + "/wda/apps/activate", param);
    }

    /**
     * 健康检查
     * wda会按一次物理键（圆形Home键）
     */
    public boolean healthCheck() {
        BaseResponse r = get(getBaseUrl() + "/wda/healthcheck");
        return r.isSuccess() && r.getValue() == null;
    }
}
