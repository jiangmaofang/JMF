package cn.com.jmf.learn.validator;

import cn.com.jmf.learn.dto.DataErrorDTO;
import cn.com.jmf.learn.enums.OperationType;
import cn.com.jmf.learn.exception.DataCheckException;
import cn.com.jmf.learn.utils.Common;
import cn.com.jmf.learn.utils.MessageUtil;
import cn.com.jmf.learn.utils.Msg;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 针对验证功能的一些方法
 * @author zhouliangfei
 * @version v 1.0
 * @date 2017/3/22 14:26
 **/
public class ValidatorUtils {

    /**
     * 单个对象验证多验证器验证
     * @param checkList
     * @param object
     * @param operationType 操作类型，针对不同操作，校验不同情况
     * */
    public static void validation(List<DataValidator> checkList, Object object, OperationType operationType) {
        if (!CollectionUtils.isEmpty(checkList) || object != null) {
            //@Order 验证器排序
            AnnotationAwareOrderComparator.sort(checkList);
            for (DataValidator validator : checkList) {
                validator.validation(object, operationType);
            }
        }
    }

    /**
     * 单个对象验证多验证器验证
     * @param checkList
     * @param object
     * */
    public static void validation(List<DataValidator> checkList, Object object) {
        validation(checkList, object, null);
    }

    /**
     * 数据是否检查有异常
     * @param dataList
     * @return boolean true 存在异常 / false 不存在
     * */
    public static <T extends DataErrorDTO> boolean isError(List<T> dataList) {
        if (!CollectionUtils.isEmpty(dataList)) {
            for (DataErrorDTO dto : dataList) {
                if (dto.isErrored()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断数据是否重复
     * @param existMap
     * @param dto
     * @param mainKey 可以自由组成
     * @return int 大于1已经存在
     * */
    public static <T extends DataErrorDTO> int isExist(Map<String, Integer> existMap, T dto, String mainKey) {
        int index = dto.getIndex();
        Integer exist = existMap.get(mainKey);
        if (exist != null && exist > 0) {
            return exist;
        } else {
            existMap.put(mainKey, index);
        }
        return 0;
    }


    /**
     * 是否中文
     * @param str
     * */
    public static boolean isContainChinese(String str) {
        Pattern pattern = Pattern.compile(Common.CHINESE_REG);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    public static boolean isNull(Date date) {
        return date == null;
    }

    public static boolean isNull(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isNull(BigDecimal amount) {
        return amount == null;
    }

    /**
     * 判断时间是否为空
     * @param date      时间
     * @param fieldName 属性名
     * @return boolean
     * */
    public static boolean isNull( Date date, String fieldName) {
        if (isNull(date)) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        }
        return false;
    }

    public static boolean isNull(Integer it, String fieldName) {
        if (it == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     * @param str       字符
     * @param fieldName 属性名
     * @return boolean
     * */
    public static boolean isNull(String str, String fieldName) {
        if (isNull(str)) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        }
        return false;
    }

    /**
     * 判断金额是否为空
     * @param amount    金额
     * @param fieldName 属性名
     * @return boolean
     * */
    public static boolean isNull(BigDecimal amount, String fieldName) {
        if (isNull(amount)) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        }
        return false;
    }

    /**
     * 匹配是否为规定的参数
     * @param value 参数值
     * @param array 参数值数组，匹配是否是对应的值
     * @param fieldName 属性名
     * @return boolean value
     *
     * */
    public static boolean valueIsOption(String value, String[] array, String fieldName) {
        if (!isNull(value, fieldName)) {
            //匹配是否符合数组中定义的类型
            return valueIsOptionNull(value, array, fieldName);
        }
        return false;
    }


    /**
     * 匹配是否为规定的参数
     * 可以为空
     * @param value 参数值
     * @param array 参数值数组，匹配是否是对应的值
     * @param fieldName 属性名
     * @return boolean value
     * */
    public static boolean valueIsOptionNull(String value, String[] array, String fieldName) {
        if (!isNull(value)) {
            //匹配是否符合数组中定义的类型
            boolean isFlag = false;
            for (String str : array) {
                if (value.equals(str)) {
                    isFlag = true;
                    break;
                }
            }
            //值错误
            if (!isFlag) {
                throw new DataCheckException(MessageUtil.format(Msg.NOT_FOUND, fieldName));
            }
        }
        return false;
    }

    /**
     * 验证大于0的金额
     * @param amount    金额
     * @param fieldName 等于空消息
     * */
    public static boolean checkAmountGtZero(BigDecimal amount, String fieldName) {
        if (amount == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        } else if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DataCheckException(MessageUtil.format(Msg.GT_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 验证大于等于0的金额或者数字
     *  x == y  0
     *  x <  y -1
     *  x >  y 1
     * @param amount    金额或者数字
     * @param fieldName 等于空消息
     * */
    public static boolean checkGeZero(BigDecimal amount, String fieldName) {
        if (amount == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DataCheckException(MessageUtil.format(Msg.GE_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 验证大于0的金额或者数字
     *  x == y  0
     *  x <  y -1
     *  x >  y 1
     * @param amount    金额或者数字
     * @param fieldName 等于空消息
     * */
    public static boolean checkGtZero(BigDecimal amount, String fieldName) {
        if (amount == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        } else if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new DataCheckException(MessageUtil.format(Msg.GT_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 验证大于等于0的金额或者数字
     * @param num    数值
     * @param fieldName 等于空消息
     * */
    public static boolean checkGeZero(Integer num, String fieldName) {
        if (num == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        } else if (!(num >= 0)) {
            throw new DataCheckException(MessageUtil.format(Msg.GE_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 验证大于0的数值
     * @param num    数值
     * @param fieldName 等于空消息
     * */
    public static boolean checkGtZero(Integer num, String fieldName) {
        if (num == null) {
            throw new DataCheckException(MessageUtil.format(Msg.CAN_NOT_NULL, fieldName));
        } else if (!(num > 0)) {
            throw new DataCheckException(MessageUtil.format(Msg.GT_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 验证大于等于0的金额,可以为空
     * @param amount    金额
     * @param fieldName 等于空消息
     * */
    public static boolean checkGeZeroEmpty(BigDecimal amount, String fieldName) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) < 0) {
           throw new DataCheckException(MessageUtil.format(Msg.GT_ZERO, fieldName));
        }
        return false;
    }

    /**
     * 结束时间要大于开始时间
     * begin < end
     * @param begin     开始时间
     * @param end       结束时间
     * @param msg       提示消息
     * */
    public static void dateCompareLT(Date begin, Date end, String msg) {
        if (begin == null || end == null) {
            return;
        }
        //结束时间要大于开始时间
        if (!(begin.compareTo(end) == -1)) {
            throw new DataCheckException(msg);
        }
    }

    /**
     * 结束时间大于等于开始时间
     * begin <= end
     * @param errorList 异常列表
     * @param begin     开始时间
     * @param end       结束时间
     * @param msg       提示消息
     * */
    public static void dateCompareLE(List<String> errorList, Date begin, Date end, String msg) {
        if (begin == null || end == null) {
            return;
        }
        if (begin.compareTo(end) == 1) {
            throw new DataCheckException(msg);
        }
    }

    /**
     * 两个数据是否一样
     * @param errorList 异常列表
     * @param str1      数据1
     * @param str2      数据2
     * @param msg       提示消息
     * */
    public static void dataEQ(List<String> errorList, String str1, String str2, String msg) {
        if (StringUtils.isAnyEmpty(str1, str2)) {
            return;
        }
        if (!str1.equals(str2)) {
            throw new DataCheckException(msg);
        }
    }

    /**
     * 两个数据是否一样
     * @param errorList 异常列表
     * @param bd1      数据1
     * @param bd2      数据2
     * @param msg       提示消息
     * */
    public static void dataEQ(List<String> errorList, BigDecimal bd1, BigDecimal bd2, String msg) {
        if (bd1 == null || bd2 == null) {
            return;
        }
        if (bd1.compareTo(bd2) != 0) {
            throw new DataCheckException(msg);
        }
    }

    /**
     * 两个数据是否一样
     * @param errorList 异常列表
     * @param date1      数据1
     * @param date2      数据2
     * @param msg       提示消息
     * */
    public static void dataEQ(List<String> errorList, Date date1, Date date2, String msg) {
        if (date1 == null || date2 == null) {
            return;
        }
        if (date1.compareTo(date2) != 0) {
            throw new DataCheckException(msg);
        }
    }

}
