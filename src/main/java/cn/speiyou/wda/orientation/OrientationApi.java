package cn.speiyou.wda.orientation;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import com.alibaba.fastjson.JSON;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 5:37 下午
 */
public class OrientationApi extends BaseApi {

    public OrientationApi(WDAClient client) {
        super(client);
    }

    /**
     * 获取当前手机屏幕的方向
     * 横向 - LANDSCAPE
     * 竖向 - PORTRAIT
     * @param sessionId
     * @return
     */
    public BaseResponse<String> getOrientation(String sessionId) {
        return get(getBaseUrlWithSession(sessionId) + "/orientation", null);
    }
}
