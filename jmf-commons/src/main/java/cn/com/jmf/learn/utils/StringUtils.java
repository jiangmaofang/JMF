package cn.com.jmf.learn.utils;

/**
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-20 19:06
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean equals(String code, String[] group) {
        if (group == null || group.length < 0) {
            return false;
        }
        //遍历匹配
        for (String g : group) {
            if (equals(code, g)) {
                return true;
            }
        }
        return false;
    }


    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || (objects.length == 0);
    }
}
