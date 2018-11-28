package result;

/**
 * @author Ccc
 * @date 2018/11/27 0027 下午 3:10
 */
public enum Roles {
    USER(101,"User"),
    ADMIN(110,"Admin"),
    Key(111,"RoleeeeeeeeKeyyyy");
    private final int code;
    private final String desc;

    Roles(int code, String desc) {
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
