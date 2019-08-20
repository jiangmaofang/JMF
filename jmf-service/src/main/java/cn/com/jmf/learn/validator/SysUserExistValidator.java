package cn.com.jmf.learn.validator;

import cn.com.jmf.learn.enums.OperationType;
import cn.com.jmf.learn.exception.DataCheckException;
import cn.com.jmf.learn.my7.mapper.SysUserDAO;
import cn.com.jmf.learn.my7.model.SysUser;
import cn.com.jmf.learn.service.SysUserService;
import cn.com.jmf.learn.utils.FieldName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author jiangmaofang
 * @date 2019/08/13 15:45
 */
@Order(2)
@Component
public class SysUserExistValidator implements DataValidator<SysUser> {

    @Autowired
    private SysUserDAO sysUserMapper;

    @Override
    public boolean validation(SysUser data, OperationType operationType) {

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", data.getUserName());

        List<SysUser> listSysUser = sysUserMapper.selectByExample(example);
        if(!listSysUser.isEmpty()){
            throw new DataCheckException(MessageFormat.format("{0}账号已经存在", listSysUser.get(0).getUserName()));
        }
        return true;
    }
}
