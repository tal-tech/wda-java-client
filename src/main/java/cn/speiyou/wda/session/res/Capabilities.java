package cn.speiyou.wda.session.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 4:18 下午
 */
public class Capabilities {
    // 设备类型，iphone
    private String device;
    private String browserName;
    // iOS版本
    private String sdkVersion;
    private String CFBundleIdentifier;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getCFBundleIdentifier() {
        return CFBundleIdentifier;
    }

    public void setCFBundleIdentifier(String CFBundleIdentifier) {
        this.CFBundleIdentifier = CFBundleIdentifier;
    }
}
