package cn.com.jmf.learn.utils;

import cn.com.jmf.learn.excel.ExcelRowMergeUtil;
import cn.com.jmf.learn.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * excel导出
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-28 21:08
 */
@Slf4j
public class ExcelExportUtil {

    public static <T> void export(List<T> dataList, Class<T> clazz, String sheetName,
                                  HttpServletRequest request, HttpServletResponse response) {
        ExcelUtil util = new ExcelUtil<>(clazz);
        Workbook workbook = util.exportExcel(dataList, sheetName);

        try (OutputStream os = response.getOutputStream()) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + HeaderUtil.getFileDownloadHeader(request, sheetName) + ".xls");
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            log.error("{} excel export error", sheetName, e);
        } finally {
            IOUtils.closeQuietly(workbook);
        }
    }

    //导出合并行
    public static <T> void exportRowMerge(List<T> dataList, Class<T> clazz, String sheetName, int sheetIndex, int startRow, int[] cellIndexs,
                                          HttpServletRequest request, HttpServletResponse response) {
        ExcelUtil util = new ExcelUtil<>(clazz);
        Workbook workbook = util.exportExcel(dataList, sheetName);
        //合并行
        ExcelRowMergeUtil.mergeRow(workbook, sheetIndex, startRow, cellIndexs);
        try (OutputStream os = response.getOutputStream()) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + HeaderUtil.getFileDownloadHeader(request, sheetName) + ".xls");
            workbook.write(os);


            os.flush();
        } catch (Exception e) {
            log.error("{} excel export error", sheetName, e);
        } finally {
            IOUtils.closeQuietly(workbook);
        }
    }
}
