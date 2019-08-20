package cn.com.jmf.learn.utils;

import com.alibaba.fastjson.JSON;

/**
 * json数据转换工具
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-10 14:21
 */
public class JsonUtil {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
