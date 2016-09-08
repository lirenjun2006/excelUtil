package com.fxiaoke.dataUtill;

import java.io.File;

public class CreateDate {
	
	public static void main(String[] args) throws Exception{
		int sum = 0;
		int sum1 = 0;
		for(int i=1;i<58;i++){
			String filename = "jsonsavefile.xls";
			String truejson = ReadExcel.readFirstSheetData(filename, i, 2);
			String falsejson = ReadExcel.readFirstSheetData(filename, i, 3);
			String savefilename=ReadExcel.readFirstSheetData(filename, i, 5);
			int count = creatExcelDate(truejson, falsejson, savefilename, 0);
			sum = sum + count;
		}
		
		for(int j=1;j<58;j++){
			String filename = "jsonsavefile.xls";
			String truejson = ReadExcel.readFirstSheetData(filename, j, 2);
			String falsejson = ReadExcel.readFirstSheetData(filename, j, 4);
			String savefilename=ReadExcel.readFirstSheetData(filename, j, 6);
			int count1 = creatExcelDate(truejson, falsejson, savefilename, 0);
			sum1 = sum1 + count1;
		}
		ReportUtil.log("sum="+ sum);
		ReportUtil.log("sum1="+ sum1);
		
	}

	public static int creatExcelDate(String truejson,String falsejson,String savefilename,int rownum) throws Exception{
		String filePath =CreateExcelPlus.plusNewDataRow(truejson,falsejson,savefilename,rownum);
		int count = ReadExcel.readNum(filePath);
		ReportUtil.log(count + "");
		return count;
	}
}
