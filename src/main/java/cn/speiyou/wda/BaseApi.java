package cn.speiyou.wda;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 4:03 下午
 */
public class BaseApi {

    public static final MediaType JSON_TYPE
            = MediaType.get("application/json; charset=utf-8");

    protected WDAClient wda;

    public BaseApi(WDAClient client) {
        this.wda = client;
    }

    protected OkHttpClient getHttpClient() {
        return wda.getClient();
    }

    public String getBaseUrl() {
        return wda.getBaseUrl();
    }

    public String getBaseUrlWithSession(String sessionId) {
        return String.format("%s/session/%s", getBaseUrl(), sessionId);
    }

    public String getBaseUrlWithSessionAndUUID(String sessionId, String uuid) {
        return String.format("%s/session/%s/element/%s", getBaseUrl(), sessionId, uuid);
    }

    public <T> BaseResponse<T> handleError(String res) {
        BaseResponse<Error> errRes = JSON.parseObject(res, new TypeReference<BaseResponse<Error>>(){});
        BaseResponse<T> r = new BaseResponse<>();
        r.setSuccess(false);
        r.setErr(errRes.getValue());
        return r;
    }

    /**
     * get请求
     * @param url
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> get(String url) {
        return execute(new Request.Builder().url(url).build());
    }

    /**
     * post请求
     */
    public <T> BaseResponse<T> post(String url, Object obj) {
        return execute(new Request.Builder()
                .url(url)
                .post(RequestBody.create(obj == null ? "" : JSON.toJSONString(obj), JSON_TYPE))
                .build());
    }

    /**
     * 执行一个请求
     * @param request
     * @param <T>
     * @return
     */
    private <T> BaseResponse<T> execute(Request request) {
        try (Response res = wda.getClient().newCall(request).execute()) {
            String s = Objects.requireNonNull(res.body()).string();
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            BaseResponse<T> r = JSON.parseObject(s, new TypeReference<BaseResponse<T>>(){});
            r.setSuccess(true);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }
}
