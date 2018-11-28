package result;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 3:10
 */
public enum Subject {
    LOGINSUCCESS(0,"登录成功"),
    LOGIN(1,"LOGIN");


    private final int code;
    private final String desc;

    Subject(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
