package cn.com.jmf.learn.controller;

import cn.com.jmf.learn.my7.model.SysUser;
import cn.com.jmf.learn.service.SysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangmaofang
 * @date 2019/08/13 14:48
 */
@Slf4j
@ApiSort(5)
@Api(value = "用户测试接口", tags = "用户测试接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "创建测试")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "userPassword", value = "用户密码", paramType = "query", required = true)})
    @PostMapping("/create")
    public String createSysUser(SysUser sysUser){
        boolean flag = sysUserService.createSysUser(sysUser);
        if (flag){
            return "成功";
        } else {
            return "失败";
        }
    }

    @ApiOperation(value = "修改测试")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "userPassword", value = "用户密码", paramType = "query", required = true)})
    @PostMapping("/update")
    public String updateSysUser(SysUser sysUser){
        boolean flag = sysUserService.updateSysUser(sysUser);
        if (flag){
            return "成功";
        } else {
            return "失败";
        }
    }

    @ApiOperation(value = "删除测试")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户编码", paramType = "query", required = true)})
    @PostMapping("/delete")
    public String deleteSysUser(SysUser sysUser){
        boolean flag = sysUserService.deleteSysUser(sysUser);
        if (flag){
            return "成功";
        } else {
            return "失败";
        }
    }
}
