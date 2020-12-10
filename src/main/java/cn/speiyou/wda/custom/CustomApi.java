package cn.speiyou.wda.custom;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.custom.res.ActiveAppInfo;
import cn.speiyou.wda.custom.res.WDADeviceInfo;
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
        Request request = new Request.Builder()
                .url(getBaseUrlWithSession(session) + "/wda/deactivateApp")
                .post(RequestBody.create("", JSON_TYPE))
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            if (StringUtils.contains(s, "traceback")) {
                return handleError(s);
            }
            BaseResponse br = JSON.parseObject(s, BaseResponse.class);
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    /**
     * 解锁手机
     * @return
     */
    public BaseResponse lock() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/lock")
                .post(RequestBody.create("", JSON_TYPE))
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse br = JSON.parseObject(s, BaseResponse.class);
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }


    /**
     * 解锁手机
     * @return
     */
    public BaseResponse unlock() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/unlock")
                .post(RequestBody.create("", JSON_TYPE))
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse br = JSON.parseObject(s, BaseResponse.class);
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<ActiveAppInfo> getActiveAppInfo() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/activeAppInfo")
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse<ActiveAppInfo> br = JSON.parseObject(s, new TypeReference<BaseResponse<ActiveAppInfo>>(){});
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    /**
     * 获取当前活动的应用信息
     * @return
     */
    public BaseResponse<WDADeviceInfo> getDeviceInfo() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/device/info")
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse<WDADeviceInfo> br = JSON.parseObject(s, new TypeReference<BaseResponse<WDADeviceInfo>>(){});
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    /**
     * 回到主页
     * @return
     */
    public BaseResponse homeScreen() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/homescreen")
                .post(RequestBody.create("", JSON_TYPE))
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse br = JSON.parseObject(s, BaseResponse.class);
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    /**
     * 判断当前手机屏幕是否锁屏
     * @return
     */
    public BaseResponse<Boolean> locked() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/wda/locked")
                .build();
        try (Response res = execute(request)) {
            String s = Objects.requireNonNull(res.body()).string();
            BaseResponse<Boolean> br = JSON.parseObject(s, BaseResponse.class);
            br.setSuccess(true);
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }
}
