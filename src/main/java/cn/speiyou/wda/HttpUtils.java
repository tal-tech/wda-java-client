package cn.speiyou.wda;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/15 10:50 上午
 */
public class HttpUtils {

    private static final int CONNECT_TIMEOUT = 60000 * 10;

    /**
     * get请求
     * @param url
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> get(String url, TypeReference<BaseResponse<T>> typeReference) {
        Request request = Request.Get(url).connectTimeout(CONNECT_TIMEOUT).socketTimeout(CONNECT_TIMEOUT);
        configureHeaders(request);
        return execute(request, typeReference);
    }

    /**
     * post请求
     */
    public static <T> BaseResponse<T> post(String url, Object obj, TypeReference<BaseResponse<T>> typeReference) {
        Request request = Request.Post(url).connectTimeout(CONNECT_TIMEOUT).socketTimeout(CONNECT_TIMEOUT);
        configureHeaders(request);
        configureBody(request, obj);
        return execute(request, typeReference);
    }

    /**
     * 执行一个请求
     * @param request
     * @param <T>
     * @return
     */
    private static <T> BaseResponse<T> execute(Request request, TypeReference<BaseResponse<T>> typeReference) {
        try {
            String s = getResponseContent(request);
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            if (typeReference == null) {
                BaseResponse r;
                if (StringUtils.equals("I-AM-ALIVE", s) || StringUtils.equals("Shutting down", s)) {
                    r = new BaseResponse();
                } else {
                    r = JSON.parseObject(s, BaseResponse.class);
                }
                r.setSuccess(true);
                return r;
            }
            BaseResponse<T> r = JSON.parseObject(s, typeReference);
            r.setSuccess(true);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    protected static void configureHeaders(Request request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.forEach(request::setHeader);
    }

    protected static void configureBody(Request request, Object body) {
        if (body != null) {
            request.bodyString(JSON.toJSONString(body), ContentType.APPLICATION_JSON);
        }
    }

    protected static String getResponseContent(Request request) throws IOException {
        Response response = request.execute();
        HttpResponse httpResponse = response.returnResponse();
        return handleEntity(httpResponse.getEntity()).asString(Consts.UTF_8);
    }

    protected static Content handleEntity(HttpEntity entity) throws IOException {
        return entity != null ? new Content(EntityUtils.toByteArray(entity), ContentType.getOrDefault(entity)) : Content.NO_CONTENT;
    }

    public static <T> BaseResponse<T> handleError(String res) {
        BaseResponse<Error> errRes = JSON.parseObject(res, new TypeReference<BaseResponse<Error>>(){});
        BaseResponse<T> r = new BaseResponse<>();
        r.setSuccess(false);
        r.setErr(errRes.getValue());
        return r;
    }
}
