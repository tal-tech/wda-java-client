import cn.speiyou.wda.BaseResponse;
import cn.speiyou.wda.WDAClient;
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
    private String currentSessionId;
    private String appleMapPkg = "com.apple.Maps";

    @Before
    public void init() {
        this.client = new WDAClient("127.0.0.1", 8100);
        this.currentSessionId = "FE965687-A6BB-4E49-9CF2-6654172BB20B";
    }

    @Test
    public void getPageSource() {
        String ps = this.client.getPageSource();
        assert StringUtils.isNotEmpty(ps);
    }

    @Test
    public void health() {
        // client health check
        assert this.client.health();

        // wda health check
        assert this.client.getSessionApi().healthCheck();
    }

    @Test
    public void createSession() {
        BaseResponse<CreateSession> res = this.client.getSessionApi().createSession();
        currentSessionId = res.getValue().getSessionId();
        assert StringUtils.isNotEmpty(res.getValue().getSessionId());
        System.out.println("创建Session成功：" + res.getValue().getSessionId());
    }

    @Test
    public void launchApp() {
        BaseResponse res = this.client.getSessionApi().launchApp(currentSessionId, appleMapPkg);
        assert res.getValue() == null;
    }

    @Test
    public void getAppState() {
        BaseResponse<Integer> res = this.client.getSessionApi().getAppState(currentSessionId, appleMapPkg);
        assert res.getValue() == 4;
    }

    @Test
    public void screenshot() {
        BaseResponse<String> res = this.client.getScreenshotApi().screenshot();
        assert StringUtils.isNotEmpty(res.getValue());
    }

    @Test
    public void elements() {
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setUsing(QueryUsing.CLASS_NAME);
        queryInfo.setValue("XCUIElementTypeStaticText");
        BaseResponse<List<Element>> res = this.client.getFindElementApi().elements(currentSessionId, queryInfo);
        System.out.println("查找到元素：" + JSON.toJSONString(res));
        assert res != null && res.getValue() != null && res.getValue().size() > 0;
    }
}
