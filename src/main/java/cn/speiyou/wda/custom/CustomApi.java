package cn.speiyou.wda.custom;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.custom.res.ActiveAppInfo;
import cn.speiyou.wda.custom.res.WDADeviceInfo;
import com.alibaba.fastjson.TypeReference;

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
     * @return
     */
    public BaseResponse deactiveApp() {
        return postWithSession("/wda/deactivateApp", null, null);
    }

    /**
     * 解锁手机
     * @return
     */
    public BaseResponse lock() {
        return post(getBaseUrl() + "/wda/lock", null, null);
    }


    /**
     * 解锁手机
     * @return
     */
    public BaseResponse unlock() {
        return post(getBaseUrl() + "/wda/unlock", null, null);
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<ActiveAppInfo> getActiveAppInfo() {
        return get(getBaseUrl() + "/wda/activeAppInfo",
                new TypeReference<BaseResponse<ActiveAppInfo>>(){});
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<WDADeviceInfo> getDeviceInfo() {
        return get(getBaseUrl() + "/wda/device/info",
                new TypeReference<BaseResponse<WDADeviceInfo>>(){});
    }

    /**
     * 回到主页
     * @return
     */
    public BaseResponse homeScreen() {
        return post(getBaseUrl() + "/wda/homescreen", null, null);
    }

    /**
     * 判断当前手机屏幕是否锁屏
     * @return
     */
    public BaseResponse<Boolean> locked() {
        return get(getBaseUrl() + "/wda/locked", null);
    }
}
