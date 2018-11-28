package result;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 3:10
 */
public enum Token {
    USER(101,"Token_User"),
    ADMIN(110,"Token_Admin");

    private final int code;
    private final String desc;

    Token(int code, String desc) {
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
