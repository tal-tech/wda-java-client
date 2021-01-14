package cn.speiyou.wda;

import cn.speiyou.wda.session.res.CreateSession;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

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

    public String getPathWithUUID(String uuid) {
        return "/element/" + uuid;
    }

    /**
     * 获取SessionId，如果为空，则重建一个
     * @param forceCreate 是否强制重建
     * @return
     */
    private synchronized String getSessionId(boolean forceCreate) {
        if (forceCreate || StringUtils.isEmpty(wda.getCurrentSessionId())) {
            BaseResponse<CreateSession> res = wda.getSessionApi().createSession();
            if (res.isSuccess()) {
                wda.setCurrentSessionId(res.getValue().getSessionId());
                return wda.getCurrentSessionId();
            } else {
                throw new RuntimeException("创建Session失败！");
            }
        } else {
            return wda.getCurrentSessionId();
        }
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

    /**
     * 带Session的get请求
     * ps：如果发现Session无效，则重建Session，并重新发送请求
     * @param path
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> getWithSession(String path, TypeReference<BaseResponse<T>> typeReference) {
        String sessionId = getSessionId(false);
        BaseResponse<T> res = get(getBaseUrlWithSession(sessionId) + path, typeReference);
        if (!res.isSuccess() && "invalid session id".equals(res.getErr().getError())) {
            sessionId = getSessionId(true);
            return get(getBaseUrlWithSession(sessionId) + path, typeReference);
        }
        return res;
    }

    /**
     * 带Session的post请求
     * ps：如果发现Session无效，则重建Session，并重新发送请求
     * @param path
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> postWithSession(String path, Object obj, TypeReference<BaseResponse<T>> typeReference) {
        String sessionId = getSessionId(false);
        BaseResponse<T> res = post(getBaseUrlWithSession(sessionId) + path, obj, typeReference);
        if (!res.isSuccess() && "invalid session id".equals(res.getErr().getError())) {
            sessionId = getSessionId(true);
            return post(getBaseUrlWithSession(sessionId) + path, obj, typeReference);
        }
        return res;
    }
}
