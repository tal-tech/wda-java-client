package cn.speiyou.wda;

import com.alibaba.fastjson.TypeReference;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 4:03 下午
 */
public class BaseApi {

    protected WDAClient wda;

    public BaseApi(WDAClient client) {
        this.wda = client;
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

    public <T> BaseResponse<T> get(String url, TypeReference<BaseResponse<T>> typeReference) {
        return wda.getHttpProxy().get(url, typeReference);
    }

    /**
     * post请求
     */
    public <T> BaseResponse<T> post(String url, Object obj, TypeReference<BaseResponse<T>> typeReference) {
        return wda.getHttpProxy().post(url, obj, typeReference);
    }
}
