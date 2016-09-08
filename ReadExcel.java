package com.fxiaoke.dataUtill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcel {
	
	public static String readSheetData(String filename,int sheetnum,int rownum ,int cellnum) throws IOException{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File excelpath = new File(exceldir,filename);
		POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(excelpath.toString()));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(sheetnum);
		HSSFRow row = sheet.getRow(rownum);
        HSSFCell cell = row.getCell(cellnum);
        String msg = cell.getStringCellValue();
        ReportUtil.log(msg);
        return msg;
	}
	
	public static String readFirstSheetData(String filename,int rownum ,int cellnum) throws IOException{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File excelpath = new File(exceldir,filename);
		POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(excelpath.toString()));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(rownum);
        HSSFCell cell = row.getCell(cellnum);
        String msg = cell.getStringCellValue();
//        int count =sheet.getLastRowNum();
        ReportUtil.log(msg);
        return msg;
	}
	
	public static int readNum(String filename) throws IOException{
		POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(filename));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		int count = sheet.getLastRowNum();
//        ReportUtil.log(count);
        return count;
	}

	public static int readSheetNum(String filename,int sheetnum) throws IOException{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File excelpath = new File(exceldir,filename);
		POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(excelpath.toString()));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(sheetnum);
		int count = sheet.getLastRowNum();
//      ReportUtil.log(count);
      return count;
	}
}
