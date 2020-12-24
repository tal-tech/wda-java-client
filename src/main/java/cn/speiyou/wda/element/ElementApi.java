package cn.speiyou.wda.element;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.element.res.WDARect;
import cn.speiyou.wda.element.res.WindowSize;
import cn.speiyou.wda.session.req.FromToParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/11 10:23 上午
 */
public class ElementApi extends BaseApi {

    public ElementApi(WDAClient client) {
        super(client);
    }

    /**
     * 获取窗口大小
     * @param sessionId
     * @return
     */
    public BaseResponse<WindowSize> getWindowSize(String sessionId) {
        return get(getBaseUrlWithSession(sessionId) + "/window/size", new TypeReference<BaseResponse<WindowSize>>(){});
    }

    /**
     * 检查某元素是否禁用了
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> enabled(String sessionId, String elementUUID) {
        return get("/enabled", sessionId, elementUUID, null);
    }

    /**
     * 检查某元素的大小
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<WDARect> rect(String sessionId, String elementUUID) {
        return get("/rect", sessionId, elementUUID, new TypeReference<BaseResponse<WDARect>>(){});
    }

    /**
     * 检查某元素的文本
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> text(String sessionId, String elementUUID) {
        return get("/text", sessionId, elementUUID, null);
    }

    /**
     * 获取某个节点的属性值
     * @param sessionId
     * @param elementUUID
     * @param attrName
     * @return
     */
    public BaseResponse<String> attrValue(String sessionId, String elementUUID, String attrName) {
        return get("/attribute/" + attrName, sessionId, elementUUID, null);
    }

    /**
     * 检查某元素是否展现出来
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> displayed(String sessionId, String elementUUID) {
        return get("/displayed", sessionId, elementUUID, null);
    }

    /**
     * 检查某元素是否被选择
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> selected(String sessionId, String elementUUID) {
        return get("/selected", sessionId, elementUUID, null);
    }

    /**
     * 检查某元素的名称
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> name(String sessionId, String elementUUID) {
        return get("/name", sessionId, elementUUID, null);
    }

    /**
     * 对控件截图
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> screenshot(String sessionId, String elementUUID) {
        return get("/screenshot", sessionId, elementUUID, null);
    }

    /**
     * 针对某个元素调用某方法
     * @param sessionId
     * @param elementUUID
     * @return
     */
    private <T> BaseResponse<T> get(String name, String sessionId, String elementUUID, TypeReference<BaseResponse<T>> typeReference) {
        return get(getBaseUrlWithSessionAndUUID(sessionId, elementUUID) + name, typeReference);
    }

    /**
     * 给控件设置值
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse value(String sessionId, String elementUUID, String value) {
        JSONObject obj = new JSONObject();
        obj.put("value", value);
        return post("/value", sessionId, elementUUID, obj, null);
    }

    /**
     * 点击控件
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse click(String sessionId, String elementUUID) {
        return post("/click", sessionId, elementUUID, null, null);
    }

    /**
     * 清空输入框
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse clear(String sessionId, String elementUUID) {
        return post("/clear", sessionId, elementUUID, null, null);
    }

    /**
     * 控件滑动
     * 将一个控件往某个方向滚动多少距离。
     * 包含两个参数：
     * direction表示滚动方向，可选值有：up、down、left、right
     * velocity表示滚动速度，只建议从50-100，值越大速度越快
     * { "direction": "down" }
     * @param sessionId
     * @param elementUUID
     * @param direction 往某个方向滑动
     * @param velocity 滑动速度
     * @return
     */
    public BaseResponse swip(String sessionId, String elementUUID, String direction, int velocity) {
        JSONObject json = new JSONObject();
        json.put("direction", direction);
        json.put("velocity", velocity);
        return post("/swip", sessionId, elementUUID, json, null);
    }

    /**
     * 长按一个控件，可以设置长按时间，时间单位为秒
     * @param sessionId
     * @param elementUUID
     * @param duration
     * @return
     */
    public BaseResponse touchAndHold(String sessionId, String elementUUID, double duration) {
        JSONObject json = new JSONObject();
        json.put("duration", duration);
        return post("/touchAndHold", sessionId, elementUUID, json, null);
    }

    /**
     * 拖动一个控件从哪个地方到那个地方
     * @param sessionId
     * @param elementUUID
     * @param param
     * @return
     */
    public BaseResponse dragFromToForDuration(String sessionId, String elementUUID, FromToParam param) {
        return post("/dragfromtoforduration", sessionId, elementUUID, param, null);
    }

    /**
     * 在屏幕上拖动
     * @param sessionId
     * @param param
     * @return
     */
    public BaseResponse dragFromToForDurationInCoordinate(String sessionId, FromToParam param) {
        return post(getBaseUrlWithSession(sessionId) + "/wda/dragfromtoforduration", param, null);
    }

    /**
     * 拨动滚轮控件
     * 找到一个滚动，向上或向下滚动，滚动幅度取值从0.1到0.5，0.1表示一格，最大5格
     *
     * 被选定的控件的类型必须为：XCUIElementTypePickerWheel，否则报错。
     *
     * {
     *     "order": "next",  // 取值为next或者previous，不区分大小写
     *     "offset": 0.1 // 滚动幅度取值从0.1到0.5，0.1表示一格，最大5格
     * }
     * @param sessionId
     * @param uuid
     * @param order 滚动方向， next or pre
     * @param offset 已经变成整形了，取值从1-5
     * @return
     */
    public BaseResponse pickWheel(String sessionId, String uuid, String order, int offset) {
        JSONObject obj = new JSONObject();
        obj.put("order", order);
        obj.put("offset", (float) offset / 10);
        return post(getBaseUrlWithSession(sessionId)
                + String.format("/wda/pickerwheel/%s/select", uuid), obj, null);
    }

    /**
     * 对有输入焦点的控件输入字符串
     * 参数：
     *
     * 字段名	含义	示例
     * value	要输入的字符串数组	["hello world", "dddd"]
     * frequency	输入速度，整形，数字越大速度越快	10
     * 备注：
     *
     * 如果当前界面没有输入焦点的控件，这个操作会等待一段时间直到超时或者有焦点的控件出现
     * @param sessionId
     * @param keys
     * @param frequency
     * @return
     */
    public BaseResponse keys(String sessionId, int frequency, List<String> keys) {
        JSONObject json = new JSONObject();
        json.put("value", keys);
        json.put("frequency", frequency);
        return post(getBaseUrlWithSession(sessionId) + "/wda/keys", json, null);
    }

    /**
     * 对有输入焦点的控件输入字符串
     * 参数：
     *
     * 字段名	含义	示例
     * value	要输入的字符串数组	["hello world", "dddd"]
     * frequency	输入速度，整形，数字越大速度越快	10
     * 备注：
     *
     * 如果当前界面没有输入焦点的控件，这个操作会等待一段时间直到超时或者有焦点的控件出现
     * @param sessionId
     * @param keys 字符串数组
     * @param frequency 输入速度
     * @return
     */
    public BaseResponse keys(String sessionId, int frequency, String... keys) {
        JSONObject json = new JSONObject();
        json.put("value", keys);
        json.put("frequency", frequency);
        return post(getBaseUrlWithSession(sessionId) + "/wda/keys", json, null);
    }

    /**
     * 长按某个控件，支持设定压力和时延，以及控件内的某个位置
     *
     * 参数表：
     *
     * 字段名称	含义	示例
     * pressure	压力值	0.5
     * duration	按压的时间，单位秒	3
     * x	该控件内的某个点的x值，可选	10
     * y	该控件内的某个点的y值，可选	10
     * @param session
     * @param elementUUID
     * @param x 控件内的x值
     * @param y 控件内的y值
     * @param duration 时延，单位秒
     * @param pressure 压力值，如0.5
     * @return
     */
    public BaseResponse forceTouch(String session, String elementUUID, int x, int y, int duration, double pressure) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        json.put("duration", duration);
        json.put("pressure", pressure);
        return post(getBaseUrlWithSession(session) +
                String.format("/wda/element/%s/forceTouch", elementUUID), json, null);
    }

    /**
     * 以某个元素的左上角为原点，点击某个位置
     * @param session
     * @param elementUUID 可以为空，当为空时，相对屏幕左上角为原点，非空时相对此元素的左上角为原点
     * @param x
     * @param y
     * @return
     */
    public BaseResponse tap(String session, String elementUUID, double x, double y) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        String url = getBaseUrlWithSession(session) + "/wda/tap";
        if (StringUtils.isNotEmpty(elementUUID)) {
            url += "/" + elementUUID;
        } else {
            url += "/null";
        }
        return post(url, json, null);
    }

    /**
     * 坐标双击
     * 指定位置双击
     *
     * 字段名称	含义	示例	类型
     * x	坐标x值	100	double
     * y	坐标y值	200	double
     * @param sessionId
     * @param x 屏幕上x值
     * @param y 屏幕上y值
     * @return
     */
    public BaseResponse doubleTap(String sessionId, int x, int y) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        return post(getBaseUrlWithSession(sessionId) + "/wda/doubleTap", json, null);
    }

    /**
     * 坐标长按
     * 指定位置长按
     *
     * 字段名称	含义	示例	类型
     * x	坐标x值	100	double
     * y	坐标y值	200	double
     * duration	长按时间，单位秒	0.5	double
     * @param sessionId
     * @param x
     * @param y
     * @param duration
     * @return
     */
    public BaseResponse touchAndHoldInCoordinate(String sessionId, int x, int y, int duration) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        json.put("duration", duration);
        return post(getBaseUrlWithSession(sessionId) + "/wda/touchAndHold", duration, null);
    }

    /**
     * 针对某个元素调用某方法
     * @param sessionId
     * @param elementUUID
     * @return
     */
    private <T> BaseResponse<T> post(String name, String sessionId, String elementUUID,
                                     Object obj, TypeReference<BaseResponse<T>> typeReference) {
        return post(getBaseUrlWithSessionAndUUID(sessionId, elementUUID) + name, obj, typeReference);
    }
}
