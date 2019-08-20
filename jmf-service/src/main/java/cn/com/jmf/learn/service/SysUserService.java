package cn.com.jmf.learn.service;

import cn.com.jmf.learn.my7.model.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiangmaofang
 * @date 2019/08/13 14:58
 */
public interface SysUserService {

    /**
     * 修改用户
     * @param sysUser
     * @return boolean
     */
    boolean updateSysUser(SysUser sysUser);

    /**
     * 创建
     * @param sysUser
     * @return boolean
     */
    boolean createSysUser(SysUser sysUser);

    /**
     * 删除
     * @param sysUser
     * @return boolean
     */
    boolean deleteSysUser(SysUser sysUser);

    /**
     * 查询
     * @param sysUser
     * @return boolean
     */
    List<SysUser> listSysUser(SysUser sysUser);
}
