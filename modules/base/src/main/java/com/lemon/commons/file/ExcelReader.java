package com.lemon.commons.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	/**
	 * 创建工作簿对象
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static final Workbook readWb(String filePath) throws IOException {
		if (StringUtils.isBlank(filePath)) {
			throw new IllegalArgumentException("参数错误!!!");
		}
		if (filePath.trim().toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(new FileInputStream(filePath));
		} else if (filePath.trim().toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(new FileInputStream(filePath));
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}

	public static final Sheet getSheet(Workbook wb, String sheetName) {
		return wb.getSheet(sheetName);
	}

	public static final Sheet getSheet(Workbook wb, int index) {
		return wb.getSheetAt(index);
	}

	public static final List<Object[]> listFromSheet(Sheet sheet) {

		List<Object[]> list = new ArrayList<Object[]>();
		for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null || row.getLastCellNum() <= 0)
				continue;
			// 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
			// 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，
			Object[] cells = new Object[row.getLastCellNum()];
			for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				if (cell == null)
					continue;
				cells[c] = getValueFromCell(cell);
			}
			list.add(cells);
		}

		return list;
	}
	/**
	 * 创建excel文档，
	 *
	 * @param list
	 *            数据
	 * @param keys
	 *            list中map的key数组集合
	 * @param columnNames
	 *            excel的列名
	 * */
	public static Workbook createByList(List<Map<String, Object>> list, String[] keys, String columnNames[], String sheetName) {
		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		createByWorkbookSheetName(wb, list, keys, columnNames, sheetName);
		return wb;
	}

	public static Workbook createByWorkbookSheetName(Workbook wb, List<Map<String, Object>> list, String[] keys, String columnNames[], String sheetName) {
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet("" + sheetName);
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		// 创建第一行
		Row row = sheet.createRow((short) 0);
		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		setWorkbook(wb, cs, cs2);
		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (short i = 1; i <= list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow(i);
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? " " : list.get(i - 1).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
		}
		return wb;
	}

	private static void setWorkbook(Workbook wb, CellStyle cs, CellStyle cs2) {

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
	}

	/**
	 * 获取单元格内文本信息
	 *
	 * @param cell
	 * @return
	 * @date 2013-5-8
	 */
	public static final String getValueFromCell(Cell cell) {
		if (cell == null) {
			return null;
		}
		String value = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING : // 字符串
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN : // Boolean
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR : // Error，返回错误码
				value = String.valueOf(cell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC : // 数字类型
				double  tmp = cell.getNumericCellValue();
				BigDecimal bigDecimal = new BigDecimal(tmp);
				value = bigDecimal.toString();
				break;
			default :
				value = StringUtils.EMPTY;
				break;
		}

		if(value!=null){
			value = value.trim();
		}
		// 使用[]记录坐标
		return value;
	}
}