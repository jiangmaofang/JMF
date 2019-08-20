package cn.com.jmf.learn.validator;

import cn.com.jmf.learn.enums.OperationType;
import cn.com.jmf.learn.my7.model.SysUser;
import cn.com.jmf.learn.utils.FieldName;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author jiangmaofang
 * @date 2019/08/13 15:42
 */
@Order(1)
@Component
public class SysUserDataValidator implements DataValidator<SysUser> {
    @Override
    public boolean validation(SysUser data, OperationType operationType) {
        if(OperationType.UPDATE == operationType){
            ValidatorUtils.isNull(data.getUserId(), FieldName.ID);
        } else if (OperationType.INSERT == operationType) {
            ValidatorUtils.isNull(data.getUserId(), FieldName.ID);
            ValidatorUtils.isNull(data.getUserName(), FieldName.UNAME);
            ValidatorUtils.isNull(data.getUserPassword(), FieldName.UPWD);
        }
        else if (OperationType.DELETE == operationType) {
            ValidatorUtils.isNull(data.getUserId(), FieldName.ID);
        }
        return true;
    }
}
