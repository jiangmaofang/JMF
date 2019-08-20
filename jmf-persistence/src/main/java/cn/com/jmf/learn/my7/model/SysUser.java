package cn.com.jmf.learn.my7.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;

/**
 * @author jiangmaofang
 * @date 2019/08/13 9:22
 */
@Data
@NameStyle(Style.camelhumpAndUppercase)
@Table(name = "t_sys_user")
public class SysUser {

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 32)
    private String userName;

    @Column(name = "user_password", nullable = false, length = 32)
    private String userPassword;

    @Column(name = "is_delete", nullable = false, length = 1)
    private String isDelete;

}
