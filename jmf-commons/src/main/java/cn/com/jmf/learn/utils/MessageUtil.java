package cn.com.jmf.learn.utils;

import java.text.MessageFormat;

/**
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-17 18:25
 */
public class MessageUtil {

    public static String format(String msg, Object... param) {
        return MessageFormat.format(msg, param);
    }
}
