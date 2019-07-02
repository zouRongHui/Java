package org.rone.study.java.third.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * excel的解析
 * @author zouRongHui   2019年7月2日10:49:03
 */
public class ExcelMain {

    public static void main(String[] args) throws IOException {
        ExcelMain main = new ExcelMain();
//        main.createExcel();
        main.parseExcel();
    }

    /**
     * 解析excel
     * @author  zouRongHui  2019年7月2日10:08:11
     * @throws IOException
     */
    public void parseExcel() throws IOException {
        //工作簿
        Workbook workbook;
        //从本地加载
        workbook = new XSSFWorkbook(new FileInputStream(new File("/excel.xlsx")));
//        //若处理大量数据可使用SXSSFWorkbook来操作，但下下面获取sheet时需要注意
//        workbook = new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(new File("/excel.xlsx"))));

//        //web项目中从http流中加载
//        String originalFileName = org.springframework.web.multipart.MultiparFile.getOriginalFilename();
//        if (originalFileName == null || !(originalFileName.endsWith(".xlsx")) || !(originalFileName.endsWith(".xls"))) {
//            throw new IllegalArgumentException("文件格式不正确，请上传正确的excel文件");
//        }
//        if (originalFileName.endsWith(".xls")) {
//            workbook = new HSSFWorkbook(MultipartFile.getInputStream());
//        } else if (originalFileName.endsWith(".xlsx")) {
//            workbook = new XSSFWorkbook(MultipartFile.getInputStream());
//        }
        //数据表
        Sheet sheet;
        //根据sheet的名称来获取
        sheet = workbook.getSheet("sheet1");
        //根据sheet的索引来获取
        sheet = workbook.getSheetAt(0);
        Row row;
        Cell cell;
        if (workbook != null) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet = workbook.getSheetAt(i);
//                //SXSSFWorkbook对象获取sheet时需要先获取XSSFWorkbook，否则后面的Row数据为null(SXSSFWorkbook的特性)
//                sheet = ((SXSSFWorkbook) workbook).getXSSFWorkbook().getSheetAt(i);
                if (sheet != null) {
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                        row = sheet.getRow(j);
                        if (row != null) {
                            for (int k = 0; k < row.getLastCellNum(); k++) {
                                cell = row.getCell(k);
                                if (cell != null) {
                                    System.out.println("sheet:" + i + ",row: " + j + ",cell: " + k + " " + this.getCellValue(cell));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据单元格数据类型获取数据
     * @author  zouRongHui  2019年7月2日09:59:43
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object result = null;
        switch (cell.getCellType()) {
            //文本
            case STRING:
                result = cell.getStringCellValue();
                break;
            //数字(整数、小数、日期)
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期
                    result = cell.getDateCellValue();
                } else {
                    result = cell.getNumericCellValue();
                    //若数值过大会有精度损失，可使用 NumberToTextConverter.toText(cell.getNumericCellValue()) 获取未有精度损失的字符类型数据
//                    result = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            //公式
            case FORMULA:
                //实际中未遇到过，不清楚咋解析处理
                result = cell.getCellFormula();
                break;
            //空
            case BLANK:
                System.out.println("空的单元格类型: " + cell.getAddress().toString());
                break;
            //未知类型
            case _NONE:
                System.out.println("未知的单元格类型: " + cell.getAddress().toString());
                break;
            //错误
            case ERROR:
                System.out.println("错误的单元格类型: " + cell.getAddress().toString());
                break;
            default:
        }
        return result;
    }

    /**
     * 生成excel文件
     * @author  zouRongHui
     * @throws IOException
     */
    public void createExcel() throws IOException {
        //工作簿，就是一个excel对象
        Workbook workbook;
        //.xls的Workbook实现类,65535行、256列
        workbook = new HSSFWorkbook();
        //.xlsx的Workbook实现类,1048576行,16384列
        workbook = new XSSFWorkbook();
        //大数据的excel导出，未避免OOM请使用SXSSFWorkbook,1000:内存中保留的数据量
        workbook = new SXSSFWorkbook(1000);
        //设置是否压缩该临时文件
        ((SXSSFWorkbook) workbook).setCompressTempFiles(true);
        //工作表
        Sheet sheet;
        sheet = workbook.createSheet();
        //设置列宽，256为单个字符宽度(汉子算两个字符)
        sheet.setColumnWidth(0, 6 * 256);
        //数据行
        Row row;
        row = sheet.createRow(0);
        //单元格
        Cell cell;
        cell = row.createCell(0);
        //文本单元格样式
        CellStyle textStyle = this.getFormatStyle(workbook, "@");
        //设置单元格样式
        cell.setCellStyle(textStyle);
        //给单元格赋值
        cell.setCellValue("poi生成excel文件");
        //设置单元格合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        //导出到本地文件
        File file = new File("/excel.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        workbook.write(new FileOutputStream(file));
        //web项目中已流的形式返回给前端
//        workbook.write(javax.servlet.http.HttpServletResponse.getOutPutStream());
        //释放资源，不清楚用哪个
        ((SXSSFWorkbook) workbook).dispose();
        workbook.close();
    }

    /**
     * 获取单元格样式
     * @author  zouRongHui  2019年7月1日18:11:00
     * @param workbook
     * @param format    "0"：整数，"0.00"：浮点数，"@"：文本样式
     * @return
     */
    private CellStyle getFormatStyle(Workbook workbook, String format) {
        //单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        //数据格式
        cellStyle.setDataFormat(workbook.createDataFormat().getFormat(format));
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
}
