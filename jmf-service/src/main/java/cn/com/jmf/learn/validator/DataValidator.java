package cn.com.jmf.learn.validator;

import cn.com.jmf.learn.enums.OperationType;

/**
 * 数据有效性验证器
 *
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-17 16:53
 */
public interface DataValidator<T> {

    /**
     * 数据验证接口
     * @param data 需要检查验证的数据
     * @param operationType 操作类型，根据不同的操作类型，验证数据不同
     * */
    public boolean validation(T data, OperationType operationType);
}
