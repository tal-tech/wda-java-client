package cn.speiyou.wda.findelement;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.findelement.req.QueryInfo;
import cn.speiyou.wda.findelement.res.Element;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

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
    public BaseResponse<List<Element>> elements(QueryInfo queryInfo) {
        return postWithSession("/elements",
                queryInfo, new TypeReference<BaseResponse<List<Element>>>(){});
    }

    /**
     * 查找某个元素下的符合某条件的控件
     * @param parentElementUUID
     * @param queryInfo
     * @return
     */
    public BaseResponse<List<Element>> elements(String parentElementUUID, QueryInfo queryInfo) {
        return postWithSession(getPathWithUUID(parentElementUUID) + "/elements",
                queryInfo, new TypeReference<BaseResponse<List<Element>>>(){});
    }

}
