package cn.speiyou.wda.element;

import cn.speiyou.wda.BaseApi;
import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.element.res.WDARect;
import cn.speiyou.wda.element.res.WindowSize;
import cn.speiyou.wda.session.req.FromToParam;
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
     * @return
     */
    public BaseResponse<WindowSize> getWindowSize() {
        return getWithSession("/window/size", new TypeReference<BaseResponse<WindowSize>>(){});
    }

    /**
     * 检查某元素是否禁用了
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> enabled(String elementUUID) {
        return get("/enabled", elementUUID, null);
    }

    /**
     * 检查某元素的大小
     * @param elementUUID
     * @return
     */
    public BaseResponse<WDARect> rect(String elementUUID) {
        return get("/rect", elementUUID, new TypeReference<BaseResponse<WDARect>>(){});
    }

    /**
     * 检查某元素的文本
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> text(String elementUUID) {
        return get("/text", elementUUID, null);
    }

    /**
     * 获取某个节点的属性值
     * @param elementUUID
     * @param attrName
     * @return
     */
    public BaseResponse<String> attrValue(String elementUUID, String attrName) {
        return get("/attribute/" + attrName, elementUUID, null);
    }

    /**
     * 检查某元素是否展现出来
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> displayed(String elementUUID) {
        return get("/displayed", elementUUID, null);
    }

    /**
     * 检查某元素是否被选择
     * @param elementUUID
     * @return
     */
    public BaseResponse<Boolean> selected(String elementUUID) {
        return get("/selected", elementUUID, null);
    }

    /**
     * 检查某元素的名称
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> name(String elementUUID) {
        return get("/name", elementUUID, null);
    }

    /**
     * 对控件截图
     * @param elementUUID
     * @return
     */
    public BaseResponse<String> screenshot(String elementUUID) {
        return get("/screenshot", elementUUID, null);
    }

    /**
     * 针对某个元素调用某方法
     * @param elementUUID
     * @return
     */
    private <T> BaseResponse<T> get(String path, String elementUUID, TypeReference<BaseResponse<T>> typeReference) {
        return getWithSession(getPathWithUUID(elementUUID) + path, typeReference);
    }

    /**
     * 给控件设置值
     * @param elementUUID
     * @return
     */
    public BaseResponse value(String elementUUID, String value) {
        JSONObject obj = new JSONObject();
        obj.put("value", value);
        return postWithSessionAndUUID("/value", elementUUID, obj, null);
    }

    /**
     * 点击控件
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse click(String elementUUID) {
        return postWithSessionAndUUID("/click", elementUUID, null, null);
    }

    /**
     * 清空输入框
     * @param sessionId
     * @param elementUUID
     * @return
     */
    public BaseResponse clear(String sessionId, String elementUUID) {
        return postWithSessionAndUUID("/clear", elementUUID, null, null);
    }

    /**
     * 控件滑动
     * 将一个控件往某个方向滚动多少距离。
     * 包含两个参数：
     * direction表示滚动方向，可选值有：up、down、left、right
     * velocity表示滚动速度，只建议从50-100，值越大速度越快
     * { "direction": "down" }
     * @param elementUUID
     * @param direction 往某个方向滑动
     * @param velocity 滑动速度
     * @return
     */
    public BaseResponse swip(String elementUUID, String direction, int velocity) {
        JSONObject json = new JSONObject();
        json.put("direction", direction);
        json.put("velocity", velocity);
        return postWithSessionAndUUID("/swip", elementUUID, json, null);
    }

    /**
     * 长按一个控件，可以设置长按时间，时间单位为秒
     * @param elementUUID
     * @param duration
     * @return
     */
    public BaseResponse touchAndHold(String elementUUID, double duration) {
        JSONObject json = new JSONObject();
        json.put("duration", duration);
        return postWithSessionAndUUID("/touchAndHold", elementUUID, json, null);
    }

    /**
     * 拖动一个控件从哪个地方到那个地方
     * @param elementUUID
     * @param param
     * @return
     */
    public BaseResponse dragFromToForDuration(String elementUUID, FromToParam param) {
        return postWithSessionAndUUID("/dragfromtoforduration", elementUUID, param, null);
    }

    /**
     * 在屏幕上拖动
     * @param param
     * @return
     */
    public BaseResponse dragFromToForDurationInCoordinate(FromToParam param) {
        return postWithSession("/wda/dragfromtoforduration", param, null);
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
        return postWithSession(String.format("/wda/pickerwheel/%s/select", uuid), obj, null);
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
     * @param keys
     * @param frequency
     * @return
     */
    public BaseResponse keys(int frequency, List<String> keys) {
        JSONObject json = new JSONObject();
        json.put("value", keys);
        json.put("frequency", frequency);
        return postWithSession("/wda/keys", json, null);
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
     * @param keys 字符串数组
     * @param frequency 输入速度
     * @return
     */
    public BaseResponse keys(int frequency, String... keys) {
        JSONObject json = new JSONObject();
        json.put("value", keys);
        json.put("frequency", frequency);
        return postWithSession("/wda/keys", json, null);
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
        return postWithSession(String.format("/wda/element/%s/forceTouch", elementUUID), json, null);
    }

    /**
     * 以某个元素的左上角为原点，点击某个位置
     * @param elementUUID 可以为空，当为空时，相对屏幕左上角为原点，非空时相对此元素的左上角为原点
     * @param x
     * @param y
     * @return
     */
    public BaseResponse tap(String elementUUID, double x, double y) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        String url = "/wda/tap";
        if (StringUtils.isNotEmpty(elementUUID)) {
            url += "/" + elementUUID;
        } else {
            url += "/null";
        }
        return postWithSession(url, json, null);
    }

    /**
     * 坐标双击
     * 指定位置双击
     *
     * 字段名称	含义	示例	类型
     * x	坐标x值	100	double
     * y	坐标y值	200	double
     * @param x 屏幕上x值
     * @param y 屏幕上y值
     * @return
     */
    public BaseResponse doubleTap(int x, int y) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        return postWithSession("/wda/doubleTap", json, null);
    }

    /**
     * 坐标长按
     * 指定位置长按
     *
     * 字段名称	含义	示例	类型
     * x	坐标x值	100	double
     * y	坐标y值	200	double
     * duration	长按时间，单位秒	0.5	double
     * @param x
     * @param y
     * @param duration
     * @return
     */
    public BaseResponse touchAndHoldInCoordinate(int x, int y, int duration) {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        json.put("duration", duration);
        return postWithSession("/wda/touchAndHold", json, null);
    }

    /**
     * 针对某个元素调用某方法
     * @param elementUUID
     * @return
     */
    private <T> BaseResponse<T> postWithSessionAndUUID(String path, String elementUUID,
                                                       Object obj, TypeReference<BaseResponse<T>> typeReference) {
        return postWithSession(getPathWithUUID(elementUUID) + path, obj, typeReference);
    }
}
