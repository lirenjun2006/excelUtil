package com.fxiaoke.dataUtill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

public class OperateExcel {
	public static void main(String[] args) throws Exception{
		String fromFile  = "1.0/select_data_false.xls";
		String toFile = "sample.xls" ;
		copyExcelSheet(fromFile,toFile);
	}

	public static void copyExcelSheet(String fromFile,String toFile) throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File fromFilepath = new File(exceldir,fromFile);
		File toFilepath = new File(exceldir,toFile);
		POIFSFileSystem fromFileFS =  new POIFSFileSystem(new FileInputStream(fromFilepath.toString()));
		POIFSFileSystem toFileFS =  new POIFSFileSystem(new FileInputStream(toFilepath.toString()));
		HSSFWorkbook fromWorkbook = new HSSFWorkbook(fromFileFS);
		HSSFWorkbook toWorkbook = new HSSFWorkbook(toFileFS);
//		HSSFSheet fromSheet = fromWorkbook.getSheetAt(0);
		HSSFSheet sheetCreat = fromWorkbook.cloneSheet(0);
		HSSFSheet tosheet = toWorkbook.createSheet(sheetCreat.getSheetName().toString());
		moveSheet(sheetCreat,tosheet);	
		FileOutputStream os = null;
		os = new FileOutputStream(toFile);
		toWorkbook.write(os);
		os.flush();
		os.close();
	}
	
	   private static void moveSheet(HSSFSheet sheetCreat, HSSFSheet toSheet) {
	        int sheetMergerCount = toSheet.getNumMergedRegions();
	        for (int i = 0; i < sheetMergerCount; i++) {
	            CellRangeAddress mergedRegionAt = toSheet.getMergedRegion(i);
	            sheetCreat.addMergedRegion(mergedRegionAt);
	        }

	    }
}
