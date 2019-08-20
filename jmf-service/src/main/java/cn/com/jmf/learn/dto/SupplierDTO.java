package cn.com.jmf.learn.dto;

import cn.com.jmf.learn.excel.annotation.Excel;
import lombok.Data;

/**
 * @author jiangmaofang
 * @date 2019/08/05 17:39
 */
@Data
public class SupplierDTO {

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String custName;

    /**
     * 公司邮箱
     */
    @Excel(name = "公司邮箱")
    private String level;

    /**
     * 积分/月饼礼盒
     */
    @Excel(name = "积分/月饼礼盒")
    private String submitDep;

}
