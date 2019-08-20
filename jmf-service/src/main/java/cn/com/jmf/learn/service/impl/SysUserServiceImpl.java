package cn.com.jmf.learn.service.impl;

import cn.com.jmf.learn.enums.OperationType;
import cn.com.jmf.learn.my7.mapper.SysUserDAO;
import cn.com.jmf.learn.my7.model.SysUser;
import cn.com.jmf.learn.service.SysUserService;
import cn.com.jmf.learn.utils.CommonUtil;
import cn.com.jmf.learn.validator.SysUserDataValidator;
import cn.com.jmf.learn.validator.SysUserExistValidator;
import cn.com.jmf.learn.validator.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import cn.com.jmf.learn.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;


/**
 * @author jiangmaofang
 * @date 2019/08/13 14:59
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDataValidator sysUserDataValidator;

    @Autowired
    private SysUserExistValidator sysUserExistValidator;

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    public boolean updateSysUser(SysUser sysUser) {
        sysUser.setUserPassword(MD5.GetMD5Code(sysUser.getUserPassword()));
        ValidatorUtils.validation(Arrays.asList(sysUserDataValidator), sysUser, OperationType.UPDATE);
        sysUserDAO.updateByPrimaryKeySelective(sysUser);
        return true;
    }

    @Override
    public boolean createSysUser(SysUser sysUser) {
        sysUser.setUserId(CommonUtil.getUUID());
        sysUser.setUserPassword(MD5.GetMD5Code(sysUser.getUserPassword()));
        ValidatorUtils.validation(Arrays.asList(sysUserDataValidator, sysUserExistValidator), sysUser, OperationType.INSERT);
        sysUserDAO.insert(sysUser);
        return true;
    }

    @Override
    public boolean deleteSysUser(SysUser sysUser){
        ValidatorUtils.validation(Arrays.asList(sysUserDataValidator), sysUser, OperationType.DELETE);
        sysUserDAO.deleteByPrimaryKey(sysUser.getUserId());
        return true;
    }


    @Override
    public List<SysUser> listSysUser(SysUser sysUser){
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", sysUser.getUserName());
        criteria.andEqualTo("userPassword", MD5.GetMD5Code(sysUser.getUserPassword()));
        List<SysUser> listSysUser = sysUserDAO.selectByExample(criteria);
        return listSysUser;
    }
}
