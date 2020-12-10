package cn.speiyou.wda.custom.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 6:55 下午
 */
public class ActiveAppInfo {

    // 应用名
    private String name;
    // 应用进程名
    private String pid;
    // 应用包名
    private String bundleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
}
