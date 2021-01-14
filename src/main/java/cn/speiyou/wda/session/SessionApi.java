package cn.speiyou.wda.session;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.session.req.AppParam;
import cn.speiyou.wda.session.res.CreateSession;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

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
        JSONObject json = new JSONObject();
        json.put("capabilities", new JSONObject());
        return post(getBaseUrl() + "/session", json,
                new TypeReference<BaseResponse<CreateSession>>(){});
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse launchApp(String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return postWithSession("/wda/apps/launch", param, null);
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse<Integer> getAppState(String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return postWithSession("/wda/apps/state", param, null);
    }

    /**
     * 激活应用
     * @param bundleId 应用包id
     */
    public BaseResponse activateApp(String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return postWithSession("/wda/apps/activate", param, null);
    }

    /**
     * 关闭某app
     * @param bundleId
     * @return
     */
    public BaseResponse<Boolean> terminateApp(String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        return postWithSession("/wda/apps/terminate", param, null);
    }

    /**
     * 健康检查
     * wda会按一次物理键（圆形Home键）
     */
    public BaseResponse healthCheck() {
        return get(getBaseUrl() + "/wda/healthcheck", null);
    }
}
