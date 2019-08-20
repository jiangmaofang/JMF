package cn.com.jmf.learn.excel;

import cn.com.jmf.learn.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 行合并
 * @author zhouliangfei
 */
@Slf4j
public class ExcelRowMergeUtil {


    /**
     * 合并行列表
     * @param workbook
     * @param sheetIndex
     * */
    public static void mergeRow(Workbook workbook, int sheetIndex, int startRow, int[] cellIndexs) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int rows = sheet.getPhysicalNumberOfRows();
        int beginIndex = 0;
        int endIndex = 0;
        String value = null;
        String bValue = null;
        Row row = null;
        Cell cell = null;
        boolean restarted = true;
        CellRangeAddress cellRangeAddress = null;
        //样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //遍历要合并的列
        for (int i = 0; i < cellIndexs.length; i ++) {
            int cellIndex = cellIndexs[i];
            restarted = true;
            //遍历行
            for (int idx = 0; idx < rows; idx++) {
                if (idx < startRow - 1) {
                    continue;
                }
                row = sheet.getRow(idx);
                if (row == null) {
                    break;
                }
                cell = row.getCell(cellIndex);
                if (cell == null) {
                    break;
                }
                cell.setCellStyle(cellStyle);
                value = cell.getStringCellValue();
                if (restarted) {
                    beginIndex = idx;
                    bValue = value;
                    restarted = false;
                }

                //不一致时合并
                if (!StringUtils.equals(value, bValue)) {
                    endIndex = idx - 1;
                    //只占一行的，不合并
                    if (endIndex == beginIndex) {
                        beginIndex = idx;
                        bValue = value;
                        continue;
                    }

                    //合并行
                    cellRangeAddress = new CellRangeAddress(beginIndex, endIndex,
                            cellIndex, cellIndex);
                    sheet.addMergedRegion(cellRangeAddress);

                    beginIndex = idx;
                    bValue = value;
                }

                //读到最后一行的时候...
                if (idx == rows - 1) {
                    endIndex = idx;
                    if (beginIndex < endIndex) {
                        cellRangeAddress = new CellRangeAddress(beginIndex, endIndex,
                                cellIndex, cellIndex);
                        sheet.addMergedRegion(cellRangeAddress);
                    }
                }
            }
        }
    }

}