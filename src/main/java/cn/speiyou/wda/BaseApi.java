package cn.speiyou.wda;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        return HttpUtils.get(url, typeReference);
    }

    /**
     * post请求
     */
    public <T> BaseResponse<T> post(String url, Object obj, TypeReference<BaseResponse<T>> typeReference) {
        return HttpUtils.post(url, obj, typeReference);
    }
}
