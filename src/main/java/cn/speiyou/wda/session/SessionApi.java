package cn.speiyou.wda.session;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.session.req.AppParam;
import cn.speiyou.wda.session.res.CreateSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

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
        RequestBody body = RequestBody.create("{\"capabilities\": {}}", JSON_TYPE);
        Request request = new Request.Builder()
                .url(wda.getBaseUrl() + "/session")
                .post(body)
                .build();
        try (Response res = execute(request)) {
            BaseResponse<CreateSession> r = JSON.parseObject(Objects.requireNonNull(res.body()).string(),
                    new TypeReference<BaseResponse<CreateSession>>(){});
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse launchApp(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        RequestBody body = RequestBody.create(JSON.toJSONString(param), JSON_TYPE);
        Request request = new Request.Builder()
                .url(getBaseUrlWithSession(sessionId) + "/wda/apps/launch")
                .post(body)
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            return JSON.parseObject(s, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 启动应用
     * @param bundleId 应用包id
     */
    public BaseResponse<Integer> getAppState(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        RequestBody body = RequestBody.create(JSON.toJSONString(param), JSON_TYPE);
        Request request = new Request.Builder()
                .url(getBaseUrlWithSession(sessionId) + "/wda/apps/state")
                .post(body)
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            return JSON.parseObject(s, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 激活应用
     * @param bundleId 应用包id
     */
    public BaseResponse activateApp(String sessionId, String bundleId) {
        AppParam param = new AppParam();
        param.setBundleId(bundleId);
        RequestBody body = RequestBody.create(JSON.toJSONString(param), JSON_TYPE);
        Request request = new Request.Builder()
                .url(getBaseUrlWithSession(sessionId) + "/wda/apps/activate")
                .post(body)
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            return JSON.parseObject(s, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 健康检查
     * wda会按一次物理键（圆形Home键）
     */
    public boolean healthCheck() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/healthcheck")
                .build();
        try (Response res = execute(request)) {
            BaseResponse r = JSON.parseObject(Objects.requireNonNull(res.body()).string(), BaseResponse.class);
            return r.getValue() == null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
