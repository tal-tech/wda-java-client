package cn.speiyou.wda.orientation;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;

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
    public BaseResponse<String> getOrientation() {
        return getWithSession("/orientation", null);
    }
}
