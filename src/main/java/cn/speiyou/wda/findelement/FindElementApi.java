package cn.speiyou.wda.findelement;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.Error;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.findelement.req.QueryInfo;
import cn.speiyou.wda.findelement.res.Element;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

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
        return post(getBaseUrlWithSession(sessionId) + "/elements",
                queryInfo, new TypeReference<BaseResponse<List<Element>>>(){});
    }

    /**
     * 查找某个元素下的符合某条件的控件
     * @param sessionId
     * @param parentElementUUID
     * @param queryInfo
     * @return
     */
    public BaseResponse<List<Element>> elements(String sessionId, String parentElementUUID, QueryInfo queryInfo) {
        return post(getBaseUrlWithSessionAndUUID(sessionId, parentElementUUID) + "/elements",
                queryInfo, new TypeReference<BaseResponse<List<Element>>>(){});
    }

}
