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

public class CreateExcelMult {
	public static void main(String[] args) throws Exception{
//		String filePath =multnewdata__row("parameterdata1.0/select_true.json","parameterdata1.0/select_false2.json","1.0/select_data_false.xls",0);
		String filePath =MultExistDataRow("parameterdata1.0/select_true.json","parameterdata1.0/select_false2.json","1.0/select_data_false.xls",9);
		int count = ReadExcel.readNum(filePath);
		ReportUtil.log(count + "");
	}
	
	public static String multNewDataRow(String truejson,String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File savepath = new File(exceldir,savefilename);
		JSONObject jsonObject = new JSONObject();
		String data1 = ReadjsonData.read(truejson);
		String data2 = ReadjsonData.read(falsejson);
		ReportUtil.log(data1);
		ReportUtil.log(data2);
		List<String> listkey = ReadjsonData.getAllKeys(data2);
		List<List> strs = new ArrayList<List>();
		for (int i = 0; i < listkey.size(); i++) {
//			ReportUtil.log(listkey.get(i));
			Ergodic.addList(strs, data2, listkey.get(i));
		}
		List<String> arrList = Ergodic.descart(strs);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("表1");
			for (int i = 0; i < arrList.size(); i++) {
//				ReportUtil.log(arrList.get(i));
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
				jsonObject=jsonCombine(data1,jsonObject2.toString());
//				ReportUtil.log(jsonObject.toString());
				HSSFRow row = sheet.createRow(rownum);
				rownum++;
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(jsonObject.toString());
			}
			FileOutputStream os = null;
			os = new FileOutputStream(savepath);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReportUtil.log("ok");
		return savepath.toString();
	}
	
	public static String MultExistDataRow(String truejson,String falsejson,String savefilename,int rownum) throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File exceldir = new File(classpathRoot, "excel");
		File savepath = new File(exceldir,savefilename);
		JSONObject jsonObject = new JSONObject();
		String data1 = ReadjsonData.read(truejson);
		String data2 = ReadjsonData.read(falsejson);
		ReportUtil.log(data1);
		ReportUtil.log(data2);
		List<String> listkey = ReadjsonData.getAllKeys(data2);
		List<List> strs = new ArrayList<List>();
		for (int i = 0; i < listkey.size(); i++) {
//			ReportUtil.log(listkey.get(i));
			Ergodic.addList(strs, data2, listkey.get(i));
		}
		List<String> arrList = Ergodic.descart(strs);
		try {
			POIFSFileSystem fs =  new POIFSFileSystem(new FileInputStream(savepath.toString()));
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0; i < arrList.size(); i++) {
//				ReportUtil.log(arrList.get(i));
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
				jsonObject=jsonCombine(data1,jsonObject2.toString());
//				ReportUtil.log(jsonObject.toString());
				HSSFRow row = sheet.createRow(rownum);
				rownum++;
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(jsonObject.toString());
			}
			FileOutputStream os = null;
			os = new FileOutputStream(savepath);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
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
