package cn.speiyou.wda.touch;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;

/**
 * 基于w3c协议 </br>
 * 1. https://www.w3.org/TR/webdriver/#actions </br>
 * 2. http://appium.io/docs/en/commands/interactions/actions/index.html </br>
 * @author ：cmlanche
 * @date ：Created in 2021/2/1 5:03 下午
 */
public class TouchApi extends BaseApi {

    public TouchApi(WDAClient client) {
        super(client);
    }

    /**
     * 屏幕拖动
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param duration
     * @return
     */
    public BaseResponse drag(int startX, int startY, int endX, int endY, int duration) {
        return null;
    }
}
