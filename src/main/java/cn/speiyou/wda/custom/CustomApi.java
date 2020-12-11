package cn.speiyou.wda.custom;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.custom.res.ActiveAppInfo;
import cn.speiyou.wda.custom.res.WDADeviceInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 6:09 下午
 */
public class CustomApi extends BaseApi {

    public CustomApi(WDAClient client) {
        super(client);
    }

    /**
     * 重启当前应用
     * @param session
     * @return
     */
    public BaseResponse deactiveApp(String session) {
        return post(getBaseUrlWithSession(session) + "/wda/deactivateApp", null);
    }

    /**
     * 解锁手机
     * @return
     */
    public BaseResponse lock() {
        return post(getBaseUrl() + "/wda/lock", null);
    }


    /**
     * 解锁手机
     * @return
     */
    public BaseResponse unlock() {
        return post(getBaseUrl() + "/wda/unlock", null);
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<ActiveAppInfo> getActiveAppInfo() {
        return get(getBaseUrl() + "/wda/activeAppInfo");
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<WDADeviceInfo> getDeviceInfo() {
        return get(getBaseUrl() + "/wda/device/info");
    }

    /**
     * 回到主页
     * @return
     */
    public BaseResponse homeScreen() {
        return post(getBaseUrl() + "/wda/homescreen", null);
    }

    /**
     * 判断当前手机屏幕是否锁屏
     * @return
     */
    public BaseResponse<Boolean> locked() {
        return get(getBaseUrl() + "/wda/locked");
    }
}
