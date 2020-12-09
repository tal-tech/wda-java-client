package cn.speiyou.wda.screenshot;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import com.alibaba.fastjson.JSON;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Objects;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 6:16 下午
 */
public class ScreenshotApi extends BaseApi {

    public ScreenshotApi(WDAClient client) {
        super(client);
    }

    /**
     * 健康检查
     * wda会按一次物理键（圆形Home键）
     */
    public BaseResponse<String> screenshot() {
        Request request = new Request.Builder()
                .url(getBaseUrl() + "/screenshot")
                .build();
        try (Response res = execute(request)) {
            BaseResponse<String> r = JSON.parseObject(Objects.requireNonNull(res.body()).string(), BaseResponse.class);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
