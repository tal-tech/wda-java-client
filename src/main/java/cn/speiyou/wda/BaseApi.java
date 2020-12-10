package cn.speiyou.wda;

import cn.speiyou.wda.findelement.res.Element;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

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

    /**
     * 执行一个请求
     * @param request
     * @return
     * @throws IOException
     */
    protected Response execute(Request request) throws IOException {
        return wda.getClient().newCall(request).execute();
    }

    public String getBaseUrl() {
        return wda.getBaseUrl();
    }

    public String getBaseUrlWithSession(String sessionId) {
        return String.format("%s/session/%s", getBaseUrl(), sessionId);
    }

    public <T> BaseResponse<T> handleError(String res) {
        BaseResponse<Error> errRes = JSON.parseObject(res, new TypeReference<BaseResponse<Error>>(){});
        BaseResponse<T> r = new BaseResponse<>();
        r.setSuccess(false);
        r.setErr(errRes.getValue());
        return r;
    }
}
