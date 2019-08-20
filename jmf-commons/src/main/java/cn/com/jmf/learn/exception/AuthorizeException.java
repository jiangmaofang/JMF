package cn.com.jmf.learn.exception;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class AuthorizeException extends RuntimeException {
    public AuthorizeException() {
    }

    public AuthorizeException(String message) {
        super(message);
    }

    public AuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
