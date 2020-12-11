package model.response;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 返回结果封装类
 */
public class ResponseResult extends HashMap<String, Object> implements Serializable {

    public ResponseResult() {
        super.put("code", 200);
        super.put("msg", "操作成功");
    }

    public ResponseResult(String code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    /**
     * 成功的结果
     */
    public static ResponseResult getSuccessResult() {
        return new ResponseResult();
    }

    /**
     * 失败的结果
     */
    public static ResponseResult getFailureResult(String code, String msg) {
        return new ResponseResult(code, msg);
    }

    /**
     * 发生错误的结果
     */
    public static ResponseResult getErrorResult(String code, String msg) {
        return new ResponseResult(code, msg);
    }

    /**
     * 自定义KEY的结果
     */
    @Override
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 默认KEY的结果
     */
    public <T> ResponseResult put(T data) {
        super.put("result", data);
        return this;
    }

}
