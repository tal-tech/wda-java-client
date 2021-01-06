package cn.speiyou.wda;

import cn.speiyou.wda.element.res.WDARect;
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
public class HttpProxy {

    private WDAClient client;
    private int connectTimeOut = 600000;
    private int socketTimeOut = 600000;
    // 最近一次的请求时间
    private long lastestRequestTime = 0;
    // 每次请求的间隔
    private long requestDuration = 0;

    public HttpProxy(WDAClient client) {
        this.client = client;
    }

    /**
     * get请求
     * @param url
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> get(String url, TypeReference<BaseResponse<T>> typeReference) {
        restForRequest();
        Request request = Request.Get(url).connectTimeout(connectTimeOut).socketTimeout(socketTimeOut);
        configureHeaders(request);
        return execute(request, typeReference);
    }

    /**
     * post请求
     */
    public <T> BaseResponse<T> post(String url, Object obj, TypeReference<BaseResponse<T>> typeReference) {
        restForRequest();
        Request request = Request.Post(url).connectTimeout(connectTimeOut).socketTimeout(socketTimeOut);
        configureHeaders(request);
        configureBody(request, obj);
        return execute(request, typeReference);
    }

    /**
     * 检查两次请求的时间间隔，如果太短，则要求休眠
     */
    private void restForRequest() {
        if (lastestRequestTime > 0) {
            if (System.currentTimeMillis() - lastestRequestTime > requestDuration) {
                sleep(requestDuration);
            } else {
                sleep(System.currentTimeMillis() - lastestRequestTime);
            }
        }
        lastestRequestTime = System.currentTimeMillis();
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public void setRequestDuration(long requestDuration) {
        this.requestDuration = requestDuration;
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

    private static void sleep(long time) {
        try {
            if (time > 0) {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
