package cn.com.jmf.learn.utils;

/**
 * 通用常量定义
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-16 15:28
 */
public class Common {

    //所有企业共用编码
    public static final String COMMON_CORP_CODE = "0";

    public static final String VALID_STATUS   = "0"; //有效状态数据
    public static final String INVALID_STATUS = "1"; //无效状态数据
    public static final String PRE_EFFECTIVE  = "2"; //预生效状态

    public static final String Y = "Y";
    public static final String N = "N";
    public static final String CURRENCY_JOIN_STR = "-";
    public static final String CHINESE_REG = "[\u4e00-\u9fa5]"; //中文

    public static final String ADMIN = "admin";

    public static final Integer UNIT_CORP = 0; //公司
    public static final Integer UNIT_ORGAN = 1; //区域
    public static final Integer UNIT_COMMUNITY = 2; //项目

    /**供应商级别-项目级别**/
    public static final String SUPPLIER_LEVEL_COMMUNITY = "1";
    /**供应商级别-区域战略**/
    public static final String SUPPLIER_LEVEL_ORGAN = "2";
    /**供应商级别-公司战略**/
    public static final String SUPPLIER_LEVEL_CORP = "3";

}
