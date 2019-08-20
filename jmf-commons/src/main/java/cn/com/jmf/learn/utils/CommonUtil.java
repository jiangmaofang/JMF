package cn.com.jmf.learn.utils;

import java.util.UUID;

/**
 *  uuid生成
 *
 *  @author ferren
 */
public class CommonUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String getUUID28(){
        return UUID.randomUUID().toString().replace("-","").substring(4);
    }
}
