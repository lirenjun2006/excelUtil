package com.fxiaoke.dataUtill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.JSONArray;

import net.sf.json.JSONObject;

public class CreateExcelPlus {
	
	public static void main(String[] args) throws Exception{
//		String filePath =plusnewdata__row("parameterdata1.0/select_true.json","parameterdata1.0/select_false.json","1.0/select_data_false.xls",0);
		String filePath =plusExistDataRow("parameterdata1.0/select_true.json","parameterdata1.0/select_false.json","1.0/select_data_false.xls",36);
		int count = ReadExcel.readNum(filePath);
		ReportUtil.log(count + "");
	}
	
	public static String plusNewDataRow(String truejson,String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File savepath = new File(exceldir,savefilename);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("表1");
		String data1 = ReadjsonData.read(truejson);
		String data2 = ReadjsonData.read(falsejson);
		 ReportUtil.log(data1);
		 ReportUtil.log(data2);
		List<String> listkey = ReadjsonData.getAllKeys(data2);
		try {
			for (int i = 0; i < listkey.size(); i++) {
				ReportUtil.log(listkey.get(i));
				Map<String, Object> map = new HashMap<String, Object>();
				JSONArray JSONArray1=ReadjsonData.readjsonArray(data2, listkey.get(i));
				for (int m = 0; m < JSONArray1.length(); m++) {
					if(isNum(JSONArray1.get(m).toString())){
						map.put(listkey.get(i), Integer.parseInt(JSONArray1.get(m).toString()));
					}else if (JSONArray1.get(m).toString().equals("true")||JSONArray1.get(m).toString().equals("false")){
						map.put(listkey.get(i), Boolean.parseBoolean(JSONArray1.get(m).toString()));
					}else{
						map.put(listkey.get(i), JSONArray1.get(m).toString());
					}
					JSONObject jsonObject2 = JSONObject.fromObject(map);
					JSONObject jsonObject=jsonCombine(data1,jsonObject2.toString());
//						 ReportUtil.log(jsonObject.toString());
					HSSFRow row = sheet.createRow(rownum);
					rownum++;
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(jsonObject.toString());
					FileOutputStream os = null;
					os = new FileOutputStream(savepath);
					workbook.write(os);
					os.flush();
					os.close();
		} 
		 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		ReportUtil.log("ok");
		return savepath.toString();
	}
	
	public static String plusExistDataRow(String truejson,String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File savepath = new File(exceldir,savefilename);
		POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(savepath.toString()));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		String data1 = ReadjsonData.read(truejson);
		String data2 = ReadjsonData.read(falsejson);
		 ReportUtil.log(data1);
		 ReportUtil.log(data2);
		List<String> listkey = ReadjsonData.getAllKeys(data2);
		try {
			for (int i = 0; i < listkey.size(); i++) {
				ReportUtil.log(listkey.get(i));
				Map<String, Object> map = new HashMap<String, Object>();
				JSONArray JSONArray1=ReadjsonData.readjsonArray(data2, listkey.get(i));
				for (int m = 0; m < JSONArray1.length(); m++) {
					if(isNum(JSONArray1.get(m).toString())){
						map.put(listkey.get(i), Integer.parseInt(JSONArray1.get(m).toString()));
					}else if (JSONArray1.get(m).toString().equals("true")||JSONArray1.get(m).toString().equals("false")){
						map.put(listkey.get(i), Boolean.parseBoolean(JSONArray1.get(m).toString()));
					}else{
						map.put(listkey.get(i), JSONArray1.get(m).toString());
					}
					JSONObject jsonObject2 = JSONObject.fromObject(map);
					JSONObject jsonObject=jsonCombine(data1,jsonObject2.toString());
//						 ReportUtil.log(jsonObject.toString());
					HSSFRow row = sheet.createRow(rownum);
					rownum++;
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(jsonObject.toString());
					FileOutputStream os = null;
					os = new FileOutputStream(savepath);
					workbook.write(os);
					os.flush();
					os.close();
		} 
		 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		ReportUtil.log("ok");
		return savepath.toString();
	}
	
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static JSONObject jsonCombine(String data1,String data2){
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject1 = JSONObject.fromObject(data1);
		JSONObject jsonObject2 = JSONObject.fromObject(data2);
		jsonObject.putAll(jsonObject1);
		jsonObject.putAll(jsonObject2);
		return jsonObject;
	}

}
