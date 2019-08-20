package cn.com.jmf.learn.controller;

import cn.com.jmf.learn.dto.SupplierDTO;
import cn.com.jmf.learn.excel.ExcelUtil;
import cn.com.jmf.learn.utils.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @author jiangmaofang
 * @date 2019/07/12 9:39
 */

@Slf4j
@ApiSort(5)
@Api(value = "导入导出测试接口", tags = "导入导出测试接口")
@RestController
@RequestMapping("/test")
public class HelloController {

    @ApiOperation(value = "测试")
    @GetMapping("/hello")
    public String helloSpringBoot(){
        return "你好，凯勋！,难受啊，马飞";
    }

    @ApiOperation(value = "批量导入")
    @PostMapping("/improt")
    public void exportSupplier(@RequestParam(name = "excelFile") MultipartFile excelFile,
                               HttpServletResponse response, HttpServletRequest request) throws Exception{
        InputStream inputStream = excelFile.getInputStream();
        ExcelUtil<SupplierDTO> util = new ExcelUtil<>(SupplierDTO.class);
        List<SupplierDTO> importList = util.importExcel(null, inputStream);
        ExcelExportUtil.export(importList, SupplierDTO.class, "sheet1", request, response);
    }

}
