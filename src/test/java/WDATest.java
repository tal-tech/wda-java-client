import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
import cn.speiyou.wda.custom.res.ActiveAppInfo;
import cn.speiyou.wda.custom.res.WDADeviceInfo;
import cn.speiyou.wda.element.res.WDARect;
import cn.speiyou.wda.element.res.WindowSize;
import cn.speiyou.wda.findelement.req.QueryInfo;
import cn.speiyou.wda.findelement.req.QueryUsing;
import cn.speiyou.wda.findelement.res.Element;
import cn.speiyou.wda.session.res.CreateSession;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 3:39 下午
 */
public class WDATest {

    private WDAClient client;
    private String appleMapPkg = "com.apple.Maps";
    private String targetElementUUID = "27000000-0000-0000-D303-000000000000";

    @Before
    public void init() {
        this.client = new WDAClient("127.0.0.1", 8100);
    }

    @Test
    public void getPageSource() {
        BaseResponse res = this.client.getPageSource();
        assert res.isSuccess();
    }

    @Test
    public void health() {
        // client health check
        assert this.client.health().isSuccess();

        // wda health check
        assert this.client.getSessionApi().healthCheck().isSuccess();
    }

    @Test
    public void createSession() {
        BaseResponse<CreateSession> res = this.client.getSessionApi().createSession();
        if (res.isSuccess()) {
            assert StringUtils.isNotEmpty(res.getValue().getSessionId());
            System.out.println("创建Session成功：" + res.getValue().getSessionId());
        }
        assert res.isSuccess();
    }

    @Test
    public void launchApp() {
        BaseResponse res = this.client.getSessionApi().launchApp(appleMapPkg);
        assert res.isSuccess();
    }

    @Test
    public void getAppState() {
        BaseResponse<Integer> res = this.client.getSessionApi().getAppState(appleMapPkg);
        assert res.getValue() == 4;
    }

    @Test
    public void screenshot() {
        BaseResponse<String> res = this.client.getScreenshotApi().screenshot();
        System.out.println(res.getValue());
        assert res.isSuccess();
    }

    @Test
    public void elements() {
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setUsing(QueryUsing.CLASS_NAME);
        queryInfo.setValue("XCUIElementTypeStaticText");
        BaseResponse<List<Element>> res = this.client.getFindElementApi().elements(queryInfo);
        System.out.println("查找到元素：" + JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void childElements() {
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setUsing(QueryUsing.CLASS_NAME);
        queryInfo.setValue("XCUIElementTypeStaticText");
        BaseResponse<List<Element>> res = this.client.getFindElementApi().elements(targetElementUUID, queryInfo);
        System.out.println("查找到元素：" + JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getOrientation() {
        BaseResponse<String> res = this.client.getOrientationApi().getOrientation();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void deactiveApp() {
        BaseResponse res = this.client.getCustomApi().deactiveApp();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void lock() {
        BaseResponse res = this.client.getCustomApi().lock();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void unlock() {
        BaseResponse res = this.client.getCustomApi().unlock();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getActiveAppInfo() {
        BaseResponse<ActiveAppInfo> res = this.client.getCustomApi().getActiveAppInfo();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getDeviceInfo() {
        BaseResponse<WDADeviceInfo> res = this.client.getCustomApi().getDeviceInfo();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void homeScreen() {
        BaseResponse res = this.client.getCustomApi().homeScreen();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void locked() {
        BaseResponse<Boolean> res = this.client.getCustomApi().locked();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getWindowSize() {
        BaseResponse<WindowSize> res = this.client.getElementApi().getWindowSize();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementEnabled() {
        BaseResponse<Boolean> res = this.client.getElementApi().enabled(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementRect() {
        BaseResponse<WDARect> res = this.client.getElementApi().rect(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementText() {
        BaseResponse<String> res = this.client.getElementApi().text(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementDisplayed() {
        BaseResponse<Boolean> res = this.client.getElementApi().displayed(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementSelected() {
        BaseResponse<Boolean> res = this.client.getElementApi().selected(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void elementName() {
        BaseResponse<String> res = this.client.getElementApi().name(targetElementUUID);
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getAlertButtons() {
        BaseResponse<List<String>> res = this.client.getAlertApi().getAlertButtons();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }

    @Test
    public void getAlertText() {
        BaseResponse<String> res = this.client.getAlertApi().getAlertText();
        System.out.println(JSON.toJSONString(res));
        assert res.isSuccess();
    }
}
