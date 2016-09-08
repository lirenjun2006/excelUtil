package com.fxiaoke.dataUtill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class CreateExcelAlone {
	
	public static void main(String[] args) throws Exception{
//		String filePath =alonenewdata__row("parameterdata1.0/select_false2.json","1.0/select_data_false.xls",0);
		String filePath =aloneExistDataRow("parameterdata1.0/select_false2.json","1.0/select_data_false.xls",9);
		int count = ReadExcel.readNum(filePath);
		ReportUtil.log(count + "");
	}
	
	public static String aloneNewDataRow(String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "excel");
		String filePath = appDir.toString() + "/" + savefilename;
		String data1 = ReadjsonData.read(falsejson);
		ReportUtil.log(data1);
		List<String> listkey = ReadjsonData.getAllKeys(data1);
		List<List> strs = new ArrayList<List>();
		for (int i = 0; i < listkey.size(); i++) {
			ReportUtil.log(listkey.get(i));
			Ergodic.addList(strs, data1, listkey.get(i));
		}
		List<String> arrList = Ergodic.descart(strs);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("表1");
			for (int i = 0; i < arrList.size(); i++) {
				String[] arrylist = arrList.get(i).toString().split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				for (int m = 0; m < listkey.size(); m++) {
					if(isNum(arrylist[m])){
						map.put(listkey.get(m), Integer.parseInt(arrylist[m]));
					}else if (arrylist[m].equals("true")||arrylist[m].equals("false")){
						map.put(listkey.get(m), Boolean.parseBoolean(arrylist[m]));
					}else{
						map.put(listkey.get(m), arrylist[m]);
					}
				}
				JSONObject jsonObject2 = JSONObject.fromObject(map);
				HSSFRow row = sheet.createRow(rownum);
				rownum++;
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(jsonObject2.toString());
			}
			FileOutputStream os = null;
			os = new FileOutputStream(filePath);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReportUtil.log("ok");
		return filePath;
	}
	
	public static String aloneExistDataRow(String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "excel");
		String filePath = appDir.toString() + "/" + savefilename;
		String data1 = ReadjsonData.read(falsejson);
		ReportUtil.log(data1);
		List<String> listkey = ReadjsonData.getAllKeys(data1);
		List<List> strs = new ArrayList<List>();
		for (int i = 0; i < listkey.size(); i++) {
			ReportUtil.log(listkey.get(i));
			Ergodic.addList(strs, data1, listkey.get(i));
		}
		List<String> arrList = Ergodic.descart(strs);
		try {
			POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0; i < arrList.size(); i++) {
				String[] arrylist = arrList.get(i).toString().split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				for (int m = 0; m < listkey.size(); m++) {
					if(isNum(arrylist[m])){
						map.put(listkey.get(m), Integer.parseInt(arrylist[m]));
					}else if (arrylist[m].equals("true")||arrylist[m].equals("false")){
						map.put(listkey.get(m), Boolean.parseBoolean(arrylist[m]));
					}else{
						map.put(listkey.get(m), arrylist[m]);
					}
				}
				JSONObject jsonObject2 = JSONObject.fromObject(map);
				HSSFRow row = sheet.createRow(rownum);
				rownum++;
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(jsonObject2.toString());
			}
			FileOutputStream os = null;
			os = new FileOutputStream(filePath);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReportUtil.log("ok");
		return filePath;
	}
	
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

}
