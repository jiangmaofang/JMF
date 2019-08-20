package cn.com.jmf.learn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiangmaofang
 * @date 2019/08/16 11:37
 */
@Data
public class SysUserDTO {
    private String userName;
    private String userPassword;
}
