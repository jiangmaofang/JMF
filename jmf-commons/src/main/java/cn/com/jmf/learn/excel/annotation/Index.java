package cn.com.jmf.learn.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识字段、写入数据在excel的行数
 *
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-06-06 08:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Index {
}
