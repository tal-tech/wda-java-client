package cn.speiyou.wda.findelement.req;

/**
 * @author ：cmlanche
 * @date ：Created in 2020/12/8 6:56 下午
 */
public class QueryInfo {

    // 用什么方式查找
    private String using;

    // 值是多少
    private String value;

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
