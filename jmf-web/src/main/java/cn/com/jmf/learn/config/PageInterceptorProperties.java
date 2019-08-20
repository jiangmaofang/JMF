package cn.com.jmf.learn.config;

import java.util.Properties;

/**
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-13 21:14
 */
public class PageInterceptorProperties {

    public static Properties createPageProperties(String helperDialect) {
        Properties properties = new Properties();
        properties.setProperty("helperDialect", helperDialect);
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("autoRuntimeDialect", "true");
        return properties;
    }
}
