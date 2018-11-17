package result;

/**
 * @author Ccc
 * @date 2018/11/15 0015 下午 7:10
 * 状态码枚举类
 */
public enum StatusCode {
    OK(20000,"成功"),
    ERROR(20001,"失败"),
    LOGINERROR(20002,"账号或密码错误"),
    ACCESSERROR(20003,"权限不足"),
    REMOTEERROR(20004,"远程调用失败"),
    REPERROR(20005,"重复操作")
    ;
    private final int code;
    private final String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    public static String codeOf(Integer code){
        for (StatusCode StatusCode :values() ) {
            if (StatusCode.getCode()==code){
                return StatusCode.desc;
            }
        }
        throw new RuntimeException("没有有找到对应的枚举");
    }

}
