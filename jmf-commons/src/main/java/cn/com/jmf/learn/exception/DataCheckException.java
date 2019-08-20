package cn.com.jmf.learn.exception;

/**
 * 数据校验异常
 *
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-17 17:14
 */
public class DataCheckException extends RuntimeException {

    public DataCheckException(String format, String value) {
    }

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
