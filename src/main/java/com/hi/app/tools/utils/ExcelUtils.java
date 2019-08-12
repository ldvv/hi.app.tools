package com.hi.app.tools.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelUtils {

    /**
     * 导出Excel
     * @param response 请求返回体
     * @param fileName 返回文件名
     * @param list 数据list
     * @param title 标题
     * @throws Exception
     */
    public static void exportExcel (HttpServletResponse response, String fileName, List<Map<String, Object>> list, Map<String, String> title) throws Exception{
        //创建Excel work book
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet =wb.createSheet("sheet1");

        /*************************标题**************************************/
        XSSFRow titleRow = sheet.createRow(0);

        //样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillPattern(XSSFCellStyle.FINE_DOTS); //单元格模板
        cellStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index); //单元格背景（感觉没什么卵用）
        cellStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index); //单元格前景
        cellStyle.setWrapText(true); //自动换行
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框    
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //字体
        XSSFFont font = wb.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL); //字体或者
        // font.setFontName("宋体")
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
        font.setFontHeightInPoints((short)11); //字体大小
        font.setColor(HSSFColor.RED.index); //字体颜色
        cellStyle.setFont(font); //字体添加到单元格

        //插入标题
        Set<String> keySet = title.keySet();
        List<String> keys = new ArrayList<>(keySet);
        for(int k = 0; k<keys.size(); k++){
            XSSFCell cell = titleRow.createCell(k);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title.get(keys.get(k)));
            sheet.setColumnWidth(k, title.get(keys.get(k)).length() * 256);  //单元格宽度 256（一字符）宽（汉字两字符）
        }

        //合并单元格  合并的起始行，结束行， 起始列，结束列 从0开始计算
        //sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

        /*************************数据**************************************/
        //数据样式
        XSSFDataFormat df = wb.createDataFormat();
        for(int r = 0; r < list.size(); r++){
            Map<String, Object> data = list.get(r);
            //装载数据
            XSSFRow row = sheet.createRow(r+1); //创建数据行
            for(int c = 0; c<keys.size(); c++){
                XSSFCell cell = row.createCell(c); //创建数据单元格
                String key = keys.get(c); //列标识
                Object obj = data.get(key); //单元格数据

                XSSFCellStyle cellStyleContent = wb.createCellStyle(); //单元格样式

                if(key.endsWith("Number")){
                    cell.setCellType(cell.CELL_TYPE_NUMERIC); //数据类型 --- 数字类型
//                    cell.setCellType(cell.CELL_TYPE_BOOLEAN); //数据类型 --- 布尔类型
//                    cell.setCellType(cell.CELL_TYPE_ERROR); //数据类型 --- 错误类型
//                    cell.setCellType(cell.CELL_TYPE_FORMULA); //数据类型 --- 日历类型
//                    cell.setCellType(cell.CELL_TYPE_STRING); //数据类型 --- 字符串（文本）类型
//                    cell.setCellType(cell.CELL_TYPE_BLANK); //数据类型 --- 空数据
                    cellStyleContent.setDataFormat(HSSFDataFormat.getBuiltinFormat("0")); //数字模板整数
                    cell.setCellValue(Double.valueOf(obj.toString())); //设置Double数据
                }else if(key.endsWith("Rate")){
                    cell.setCellType(cell.CELL_TYPE_NUMERIC); //数据类型 --- 数字类型
                    cellStyleContent.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%")); //数字模板百分数（保留两位小数）
                    cell.setCellValue(Double.valueOf(obj.toString())); //设置Double数据
                }else{
                    cell.setCellType(cell.CELL_TYPE_STRING); //数据类型 --- 字符串类型
                    cell.setCellValue(obj.toString()); //设置文本数据（可为null）
                }
            }
            row.setHeight((short)(256 * 1.2)); // 设置行高 256（一字符）为单倍行高
        }

        //Excel文件写到输出流
        OutputStream output=response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xlsx");
        response.setContentType("application/msexcel");
        wb.write(output);
        //关闭流
        output.close();
    }
}
