package cn.speiyou.wda.alert;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/11 4:22 下午
 */
public class AlertApi extends BaseApi {

    public AlertApi(WDAClient client) {
        super(client);
    }

    /**
     * 获取当前alert对话框的信息
     * @return 如果失败，则当前界面可能没有alert提示框
     */
    public BaseResponse<String> getAlertText() {
        return get(getBaseUrl() + "/alert/text", null);
    }

    /**
     * 获取alert对话框的按钮
     * @param sessionId
     * @return
     */
    public BaseResponse<List<String>> getAlertButtons(String sessionId) {
        return get(getBaseUrlWithSession(sessionId) + "/wda/alert/buttons",
                new TypeReference<BaseResponse<List<String>>>(){});
    }

    /**
     * 隐藏alert对话框
     * @param sessionId
     * @return
     */
    public BaseResponse dismiss(String sessionId) {
        return post(getBaseUrlWithSession(sessionId) + "/alert/dismiss", null, null);
    }

    /**
     * 点击某个按钮，隐藏对话框
     * @param sessionId
     * @param name
     * @return
     */
    public BaseResponse accept(String sessionId, String name) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        return post(getBaseUrlWithSession(sessionId) + "/alert/accept", obj, null);
    }
}
