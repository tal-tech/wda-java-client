package cn.speiyou.wda.custom.res;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/10 7:01 下午
 */
public class WDADeviceInfo {

    // 时区
    private String timeZone;
    // 当前设备使用的语言，中文还是英文等
    private String currentLocale;
    // 手机型号，iphone
    private String model;
    // 设备编号
    private String uuid;
    private int userInterfaceIdiom;
    // 设备UI风格样式，是白色还是暗黑模式
    private String userInterfaceStyle;
    // 手机名称
    private String name;
    // 是否是模拟器
    private boolean isSimulator;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserInterfaceIdiom() {
        return userInterfaceIdiom;
    }

    public void setUserInterfaceIdiom(int userInterfaceIdiom) {
        this.userInterfaceIdiom = userInterfaceIdiom;
    }

    public String getUserInterfaceStyle() {
        return userInterfaceStyle;
    }

    public void setUserInterfaceStyle(String userInterfaceStyle) {
        this.userInterfaceStyle = userInterfaceStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSimulator() {
        return isSimulator;
    }

    public void setSimulator(boolean simulator) {
        isSimulator = simulator;
    }
}
