package cn.speiyou.wda.touch.req;

/**
 * @author ：cmlanche
 * @date ：Created in 2021/2/1 5:06 下午
 */
public class TouchAction {

    public static final String POINTER_MOVE = "";
    public static final String POINTER_DOWN = "";
    public static final String POINTER_UP = "";

    /**
     * 可以是"pointer","key","null"
     */
    private String type;
    private String value;
    private int number;
    private int x;
    private int y;

    public static String getPointerMove() {
        return POINTER_MOVE;
    }

    public static String getPointerDown() {
        return POINTER_DOWN;
    }

    public static String getPointerUp() {
        return POINTER_UP;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
