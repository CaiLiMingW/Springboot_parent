package result;

import java.io.Serializable;

/**
 * @author Ccc
 * @date 2018/11/15 0015 下午 6:59
 * 消息返回类
 */
public class ServerResponse<T> implements Serializable {

    private boolean flag;
    private Integer code;
    private String message;
    private T data;

    private ServerResponse(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServerResponse(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ServerResponse(boolean flag, Integer code) {
        this.flag = flag;
        this.code = code;
    }

    private ServerResponse() {
    }

    public static<T> ServerResponse<T> CreateBySuccess(){
        return new ServerResponse<T>(true,StatusCode.OK.getCode());
    }

    public static<T> ServerResponse<T> CreateBySuccessMessage(){
        return new ServerResponse<T>(true,StatusCode.OK.getCode(),StatusCode.OK.getDesc());
    }

    public static<T> ServerResponse<T> CreateBySuccessMessage(T data){
        return new ServerResponse<T>(true,StatusCode.OK.getCode(),StatusCode.OK.getDesc(),data);
    }

    public static<T> ServerResponse<T> CreateByErrorCode(Integer code){
        return new ServerResponse<T>(false,code,StatusCode.codeOf(code));
    }

    public static<T> ServerResponse<T> CreateByErrorCode(Integer code, T data){
        return new ServerResponse<T>(false,code,StatusCode.codeOf(code),data);
    }









    public boolean isFlag() {
        return flag;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
