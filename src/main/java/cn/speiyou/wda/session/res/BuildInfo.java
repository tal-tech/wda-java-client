package cn.speiyou.wda.session.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 5:47 下午
 */
public class BuildInfo {
    private String time;
    private String productBundleIdentifier;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProductBundleIdentifier() {
        return productBundleIdentifier;
    }

    public void setProductBundleIdentifier(String productBundleIdentifier) {
        this.productBundleIdentifier = productBundleIdentifier;
    }
}
