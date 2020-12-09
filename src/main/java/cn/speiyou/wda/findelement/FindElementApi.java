package cn.speiyou.wda.findelement;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.findelement.req.QueryInfo;
import cn.speiyou.wda.findelement.res.Element;
import cn.speiyou.wda.session.req.AppParam;
import cn.speiyou.wda.session.res.CreateSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;
import java.util.Objects;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 6:54 下午
 */
public class FindElementApi extends BaseApi {

    public FindElementApi(WDAClient client) {
        super(client);
    }


    /**
     * 查找符合某个条件的所有元素
     * @param queryInfo
     * @return
     */
    public BaseResponse<List<Element>> elements(String sessionId, QueryInfo queryInfo) {
        RequestBody body = RequestBody.create(JSON.toJSONString(queryInfo), JSON_TYPE);
        Request request = new Request.Builder()
                .url(getBaseUrlWithSession(sessionId) + "/elements")
                .post(body)
                .build();
        try (Response res = execute(request)) {
            return JSON.parseObject(Objects.requireNonNull(res.body()).string(),
                    new TypeReference<BaseResponse<List<Element>>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
