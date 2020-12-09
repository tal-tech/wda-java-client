package cn.speiyou.wda.session.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 5:26 下午
 */
public class OSInfo {
    private int testmanagerdVersion;
    // iOS
    private String name;
    private String sdkVersion;
    private String version;

    public int getTestmanagerdVersion() {
        return testmanagerdVersion;
    }

    public void setTestmanagerdVersion(int testmanagerdVersion) {
        this.testmanagerdVersion = testmanagerdVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
